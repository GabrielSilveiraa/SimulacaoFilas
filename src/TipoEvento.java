public enum TipoEvento {
	SAIDA, CHEGADA;
	
	@Override
	public String toString() {
		switch(this) {
		case SAIDA:
			return "saida";
		case CHEGADA:
			return "chegada";
		}
		return "";
	}
}
