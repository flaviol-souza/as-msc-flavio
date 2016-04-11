private void createPanelFunction(final TypeFrequency type) {
		
	this.functionPanel.removeAll();
	this.m_configModel.getArgs().setTypeFrenquency(type.getName());
	int row = 0;
	
	lb_startTime = new JLabel("Start Time");
	tf_startTime = new JTextField(String.valueOf(dataSet.get(0).getFrequency().getStartTime()));
	tf_startTime.getDocument().addDocumentListener(new StartTimeListener());
	functionPanel.add(lb_startTime, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
	GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 1, 1));
	functionPanel.add(tf_startTime, new GridBagConstraints(1, row++, 1, 1, 100.0, 0.0, GridBagConstraints.WEST,
	GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
	
	lb_endTime = new JLabel("Duration Step");
	tf_endTime = new JTextField(String.valueOf(dataSet.get(0).getFrequency().getEndTime()));
	tf_endTime.getDocument().addDocumentListener(new EndTimeListener());
	functionPanel.add(lb_endTime, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
	GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 1, 1));
	functionPanel.add(tf_endTime, new GridBagConstraints(1, row++, 1, 1, 100.0, 0.0, GridBagConstraints.WEST,
	GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
	
	if (type.getName().compareTo("Pulse") == 0) {
		lb_pauseTime = new JLabel("Pause");
		tf_pauseTime = new JTextField(String.valueOf(dataSet.get(0).getFrequency().getPauseTime()));
		tf_pauseTime.getDocument().addDocumentListener(new PauseTimeListener());
		functionPanel.add(lb_pauseTime, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
		GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 1, 1));
		functionPanel.add(tf_pauseTime, new GridBagConstraints(1, row++, 1, 1, 100.0, 0.0, GridBagConstraints.WEST,
		GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
	}
	
	if (type.getName().compareTo("Step") == 0) {
		lb_polarity = new JLabel("Polarity");
		tf_polarity = new JTextField(String.valueOf(dataSet.get(0).getFrequency().getPolarity()));
		tf_polarity.getDocument().addDocumentListener(new PolarityListener());
		functionPanel.add(lb_polarity, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
		GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 1, 1));
		functionPanel.add(tf_polarity, new GridBagConstraints(1, row++, 1, 1, 100.0, 0.0, GridBagConstraints.WEST,
		GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
	}
	
	lb_quantity = new JLabel("Quantity");
	tf_quantity = new JTextField(String.valueOf(dataSet.get(0).getFrequency().getQuantity()));
	tf_quantity.getDocument().addDocumentListener(new QuantityListener());
	functionPanel.add(lb_quantity, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
	GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 1, 1));
	functionPanel.add(tf_quantity, new GridBagConstraints(1, row++, 1, 1, 100.0, 0.0, GridBagConstraints.WEST,
	GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
	
	this.functionPanel.updateUI();
	this.functionPanel.repaint();
	
}