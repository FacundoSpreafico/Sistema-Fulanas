package gestores;

public class GestorVentas {
    private static GestorVentas _INSTANCE;
	
	public static GestorVentas getInstance() {
		if (_INSTANCE == null) {
			_INSTANCE = new GestorVentas();
		}
		return _INSTANCE;
	}
}
