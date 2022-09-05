package Abstract;

public class Implementainterface implements Interface {

	@Override
	public void incluir() {
		System.out.println("Inclui");
	}

	@Override
	public void alterar() {
		System.out.println("Altera");
	}

	@Override
	public void excluir() {
		System.out.println("Exclui");
	}

	@Override
	public void imprimir() {
		System.out.println("Imprime");
	}

}
