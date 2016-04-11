public static PropertiesEB createProperties(int index, TestPhase testPhase, TypeFrequency type, long beginTime) {
		
	PropertiesEB propertiesEB = new PropertiesEB();
	Logger.getLogger().debug(type.getName());
	propertiesEB.isFrenquency = true;
	propertiesEB.setIndexEB(index);

	long timeStart = testPhase.getFrequency().getStartTime() * 1000 + beginTime;
	long timeEnd   = testPhase.getFrequency().getEndTime() * 1000 + timeStart;
	long timePause = testPhase.getFrequency().getPauseTime() * 1000;
	long timeExperiment = testPhase.getExperimentTime() * 1000 + beginTime;

	if (TypeFrequency.STEP.equals(type)) {
		if (index >= testPhase.getFrequency().getQuantity()) {
			propertiesEB.setStartTime(beginTime);
			propertiesEB.setEndTime(timeExperiment);
			propertiesEB.setEndExperimentTime(timeExperiment);
			Logger.getLogger().debug("Normal: " + index);
		} else {
			propertiesEB.setStartTime(timeStart);
			propertiesEB.setEndTime(timeEnd);
			propertiesEB.setEndExperimentTime(timeExperiment);
			Logger.getLogger().debug("To Step: " + index);
		}
	} else if (TypeFrequency.PULSE.equals(type)) {
		if (index >= testPhase.getFrequency().getQuantity()) {
			propertiesEB.setStartTime(beginTime);
			propertiesEB.setEndTime(timeExperiment);
			propertiesEB.setEndExperimentTime(timeExperiment);
			Logger.getLogger().debug("Normal: " + index);
		} else {
			propertiesEB.setStartTime(timeStart);
			propertiesEB.setPauseTime(timePause);
			propertiesEB.setEndTime(timeEnd);
			propertiesEB.setEndExperimentTime(timeExperiment);
			Logger.getLogger().debug("To Pulse: " + index);
		}
	}

	return propertiesEB;

}
