method geraCarga(ParametrosExperimento parametros, EB ebCorrente) {
	ParametrosExperimento parametros;

	ebCorrente.EmExecucao = true;
	while (parametros.ExperimentoEmExecucao) {

		//avaliando o EB segundo o tempo percorrido do experimento
		tempoCorrente = System.pegaTempoCorrente();

		if (tempoCorrente > parametros.TempoExperimento){
			ebCorrente.EmExecucao = false;
		}
					
		if (tempoCorrente > ebCorrente.TempoDuracao && ebCorrente.EbMarcado) {
			if(parametros.TempoPausa > 0){
				
				long novoInicio = ebCorrente.TempoFinal + parametros.TempoPausa ;
				long periodo = ebCorrente.TempoFinal - ebCorrente.TempoInicial;
				
				ebCorrente.TempoInicial = novoInicio;
				ebCorrente.TempoFinal = periodo + novoInicio;
			}else if (tempoCorrente > ebCorrente.TempoInicial) {
				ebCorrente.EmExecucao = false;
			}
		}

		// alguns EBs nao iniciam inmediatamente, porque foram marcados para esperar
		if (tempoCorrente >= ebCorrente.TempoInicio) {
			if (!ebCorrente.EmExecucao) {
				return;
			}
			
			
			if (ebCorrente.TemProximaPagina) {
				// 
			} else {
				ebCorrente.EmExecucao = false;
			}

			if (!ebCorrente.EmExecucao = false;) {
				return;
			}
			
		} else {
			ebCorrente.Dorme(500);				
		}
		
	}
}