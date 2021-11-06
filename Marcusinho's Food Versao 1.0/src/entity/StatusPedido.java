package entity;

public enum StatusPedido {
	ENTREGUE("Entregue"),
	EM_ANDAMENTO("Em Andamento"),
	CANCELADO("Cancelado"),
	ENVIANDO("Enviando");
	
	private String status;
	
	StatusPedido(String string) {
		this.status = string;
	}

	public String getStatus() {
		return status;
	}
}