public void test() {
		long tt = 0L; // Think Time.
		boolean sign = true;
		long startGet = System.currentTimeMillis();
		long currentTimeMillis = System.currentTimeMillis();
		this.sessionStart = startGet;
		
		
		while ((this.maxTrans == -1) || (this.maxTrans > 0)) {
			
			//avaliando o EB segundo o tempo percorrido do experimento
			currentTimeMillis = System.currentTimeMillis();
			
			if (currentTimeMillis > this.propertiesEB.getEndExperimentTime()){
				Logger.getLogger().debug(propertiesEB.getIndexEB() + " is stopping ... " + (currentTimeMillis - startExp)/1000);
				this.test = false;
			}
			
			if (currentTimeMillis > this.propertiesEB.getEndTime() && this.propertiesEB.isFrenquency()) {
				//desactivado = -1
				if(this.propertiesEB.getPauseTime() > 0){
					long newInit = this.propertiesEB.getEndTime() + this.propertiesEB.getPauseTime();
					long period = this.propertiesEB.getEndTime() - this.propertiesEB.getStartTime();
					
					this.propertiesEB.setStartTime(newInit);
					this.propertiesEB.setEndTime(period + newInit);
					
					Logger.getLogger().debug(propertiesEB.getIndexEB() + " was restarted  ... ");
				}else{
					Logger.getLogger().debug(propertiesEB.getIndexEB() + " is ending ... " + (currentTimeMillis - startExp)/1000);
					this.test = false;
				}
			}
		
			// alguns EBs nao iniciam inmediatamente, porque foram marcados para esperar
			if (currentTimeMillis >= this.propertiesEB.getStartTime()) {
				if (this.terminate || !this.test) {
					this.sessionEnd = System.currentTimeMillis();
					EBStats.getEBStats().sessionRecorder(this.sessionStart, this.sessionEnd, this.sessionLen,
					this.Ordered, this.isVIP);
					return;
				}
				
				long endGet;
				if (this.nextReq != null) {
					// Check if user session is finished.
					if (this.toHome) {
						// User session is complete. Start new user session.
						this.sessionEnd = System.currentTimeMillis();
						EBStats.getEBStats().sessionRecorder(this.sessionStart, this.sessionEnd, this.sessionLen,
						this.Ordered, this.isVIP);
						initialize();
						return;
					}
					if (this.nextReq.equals("")) {
						EBStats.getEBStats().addErrorSession(this.curState, this.isVIP);
						initialize();
						continue;
					}
					// Receive HTML response page.
					if (this.rate > 0) {
						if (isVIP) {
							if (this.nextReq.contains("?")) {
								this.nextReq += "&bench4q_session_priority=10";
							} else {
								this.nextReq += "?bench4q_session_priority=10";
							}
						} else if (this.nextReq.contains("?")) {
							this.nextReq += "&bench4q_session_priority=1";
						} else {
							this.nextReq += "?bench4q_session_priority=1";
						}
					}
			
					// additional load
					if(this.addLoad > 0 && this.addLoadOpt >= 0) {
						if (this.nextReq.contains("?")) {
							this.nextReq += "&bench4q_add_load=" + this.addLoad + "&bench4q_add_load_opt=" +this.addLoadOpt;
						} else {
							this.nextReq += "?bench4q_add_load=" + this.addLoad + "&bench4q_add_load_opt=" +this.addLoadOpt;
						}
					} else {
						if (this.nextReq.contains("?")) {
							this.nextReq += "&bench4q_add_load=0&bench4q_add_load_opt=0";
						} else {
							this.nextReq += "?bench4q_add_load=0&bench4q_add_load_opt=0";
						}
					}

					if (this.first) {
						this.m_Client = HttpClientFactory.getInstance();
						this.m_Client.getParams().setCookiePolicy(CookiePolicy.RFC_2965);
					}

					startGet = System.currentTimeMillis();
					sign = getHTML(this.curState, this.nextReq, (currentTimeMillis - startExp)/1000);	

					endGet = System.currentTimeMillis();

					if (!sign) {
						EBStats.getEBStats().addErrorSession(this.curState, this.isVIP);
						initialize();
						
						continue;
					}
					this.first = false;

					// Compute and store Web Interaction Response Time (WIRT)
					EBStats.getEBStats().interaction(this.curState, startGet, endGet, tt, this.isVIP);
					this.sessionLen++;
					if (this.curState == 4) {
						this.Ordered = true;
					}
					this.curTrans.postProcess(this, this.html);
				} else {
					this.html = null;
					endGet = startGet;
				}

				if (!nextState()) {
					return;
				}
				if (this.nextReq != null) {
					// Pick think time (TT), and compute absolute request time
					tt = MAP();
					startGet = endGet + tt;
					if ((this.terminate) || (!this.test)) {
						return;
					}
					try {
						sleep(tt);
					} catch (InterruptedException inte) {
						Thread.currentThread().interrupt();
						return;
					}	
					if (this.maxTrans > 0) {
						this.maxTrans--;
					}
				} else {
					EBStats.getEBStats().addErrorSession(this.curState, this.isVIP);
					initialize();
				}
		} else {
			try {
				// libera de sobrecarga
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}