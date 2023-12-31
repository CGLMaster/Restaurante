package Presentaci�n.Controller;



/*
 * Nomenclatura utilizada:
 * ALTA_*** = evento para generar la vista de Alta (Usado por la factor�a de vistas)
 * GUARDAR_*** = Caso de uso de Alta (Usado por la factor�a de comandos)
 * MODIFICAR_*** = evento para generar la vista de Modificar (Usado por la factor�a de vistas)
 * UPDATE_*** = Caso de uso de Modificar (Usado por la factor�a de comandos)
 * ELIMINAR_*** = Caso de uso de Eliminar (Usado por la factor�a de comando)
 * BUSCAR_TODOS_*** = Caso de uso de Buscar todos para generar la vista principal (Usado por la factor�a de comandos). Cuando se obtiene la lista de todos los objetos se llama a CREAR_V***
 * CREAR_V*** = evento para generar la vista principal (Usado por la factor�a de vistas)
 * RES_****_OK = la respuesta a una peticion se realiza correctamente
 * RES_****_KO = la respuesta a una peticion falla en algun momento
 * */


public class Eventos {
	// CLIENTES
	
		public static final int ALTA_CLIENTE = 1;

		public static final int RES_ALTA_CLIENTE_OK = 2;

		public static final int RES_ALTA_CLIENTE_KO = 3;
		
		public static final int BAJA_CLIENTE = 4;

		public static final int RES_BAJA_CLIENTE_OK = 5;

		public static final int RES_BAJA_CLIENTE_KO = 6;
		
		public static final int MODIFICAR_CLIENTE = 7;

		public static final int RES_MODIFICAR_CLIENTE_OK = 8;

		public static final int RES_MODIFICAR_CLIENTE_KO = 9;

		public static final int BUSCAR_CLIENTE = 10;

		public static final int RES_BUSCAR_CLIENTE_OK = 11;

		public static final int RES_BUSCAR_CLIENTE_KO = 12;
		
		public static final int BUSCAR_TODOS_CLIENTE = 13;

		public static final int RES_BUSCAR_TODOS_CLIENTE_OK = 14;

		public static final int RES_BUSCAR_TODOS_CLIENTE_KO = 15;
		
		public static final int CREAR_VCLIENTE = 108;
		
		public static final int GUARDAR_CLIENTE = 128;

		public static final int UPDATE_CLIENTE = 129;
		
		// TRABAJADORES

		public static final int ALTA_TRABAJADOR = 16;

		public static final int RES_ALTA_TRABAJADOR_OK = 17;

		public static final int RES_ALTA_TRABAJADOR_KO = 18;

		public static final int BAJA_TRABAJADOR = 19;

		public static final int RES_BAJA_TRABAJADOR_OK = 20;

		public static final int RES_BAJA_TRABAJADOR_KO = 21;

		public static final int MODIFICAR_TRABAJADOR = 22;

		public static final int RES_MODIFICAR_TRABAJADOR_OK = 23;

		public static final int RES_MODIFICAR_TRABAJADOR_KO = 24;

		public static final int IDENTIFICAR_TRABAJADOR = 25;

		public static final int RES_IDENTIFICAR_TRABAJADOR_OK = 26;

		public static final int RES_IDENTIFICAR_TRABAJADOR_KO = 27;
		
		public static final int BUSCAR_TODOS_TRABAJADOR = 28;

		public static final int RES_BUSCAR_TODOS_TRABAJADOR_OK = 29;

		public static final int RES_BUSCAR_TODOS_TRABAJADOR_KO = 30;
		
		public static final int BUSCAR_TRABAJADOR_DNI = 31;
		
		public static final int RES_BUSCAR_TRABAJADOR_DNI_OK = 32;
		
		public static final int RES_BUSCAR_TRABAJADOR_DNI_KO = 33;
		
		public static final int BUSCAR_TRABAJADOR_ID = 233;
		
		public static final int RES_BUSCAR_TRABAJADOR_ID_OK = 34;
		
		public static final int RES_BUSCAR_TRABAJADOR_ID_KO = 35;
		
	    public static final int CREAR_VTRABAJADOR = 110;
		
		public static final int GUARDAR_TRABAJADOR = 135;

		public static final int UPDATE_TRABAJADOR = 136;
		
		// PROVEEDORES

		public static final int ALTA_PROVEEDOR = 36;

		public static final int RES_ALTA_PROVEEDOR_OK = 37;

		public static final int RES_ALTA_PROVEEDOR_KO = 38;

		public static final int BAJA_PROVEEDOR = 39;

		public static final int RES_BAJA_PROVEEDOR_OK = 40;

		public static final int RES_BAJA_PROVEEDOR_KO = 41;

		public static final int BUSCAR_PROVEEDOR = 42;

		public static final int BUSCAR_PROVEEDOR_OK = 43;

		public static final int RES_BUSCAR_PROVEEDOR_KO = 44;

		public static final int MODIFICAR_PROVEEDOR = 45;

		public static final int RES_MODIFICAR_PROVEEDOR_OK = 46;

		public static final int RES_MODIFICAR_PROVEEDOR_KO = 47;
		
		public static final int BUSCAR_TODOS_PROVEEDOR = 48;

		public static final int RES_BUSCAR_TODOS_PROVEEDOR_OK = 49;

		public static final int RES_BUSCAR_TODOS_PROVEEDOR_KO = 50;
		
		public static final int UPDATE_PROVEEDOR = 106;

		public static final int GUARDAR_PROVEEDOR = 107;
		
		public static final int CREAR_VPROVEEDOR = 130;

		
		// MARCAS

		public static final int ALTA_MARCA = 51;

		public static final int RES_ALTA_MARCA_OK = 52;

		public static final int RES_ALTA_MARCA_KO = 53;

		public static final int BAJA_MARCA = 54;

		public static final int RES_BAJA_MARCA_OK = 55;

		public static final int RES_BAJA_MARCA_KO = 56;

		public static final int BUSCAR_MARCA = 57;

		public static final int RES_BUSCAR_MARCA_OK = 58;

		public static final int RES_BUSCAR_MARCA_KO = 59;

		public static final int MODIFICAR_MARCA = 60;

		public static final int RES_MODIFICAR_MARCA_OK = 61;

		public static final int RES_MODIFICAR_MARCA_KO = 62;

		public static final int BUSCAR_TODOS_MARCA = 63;

		public static final int RES_BUSCAR_TODOS_MARCA_OK = 64;

		public static final int RES_BUSCAR_TODOS_MARCA_KO = 65;
		
		public static final int CREAR_VMARCA = 109;
		
		public static final int GUARDAR_MARCA = 131;

		public static final int UPDATE_MARCA = 132;
		
		public static final int CREAR_VMODIFICAR_MARCA = 6699;
		
		// SECCIONES

		public static final int ALTA_SECCION = 66;

		public static final int RES_ALTA_SECCION_OK = 67;

		public static final int RES_ALTA_SECCION_KO = 68;

		public static final int BAJA_SECCION = 69;

		public static final int RES_BAJA_SECCION_OK = 70;

		public static final int RES_BAJA_SECCION_KO = 71;

		public static final int BUSCAR_SECCION = 72;

		public static final int RES_BUSCAR_SECCION_OK = 73;

		public static final int RES_BUSCAR_SECCION_KO = 74;

		public static final int MODIFICAR_SECCION = 75;

		public static final int RES_MODIFICAR_SECCION_OK = 76;

		public static final int RES_MODIFICAR_SECCION_KO = 77;

		public static final int BUSCAR_TODOS_SECCION = 78;

		public static final int RES_BUSCAR_TODOS_SECCION_OK = 79;

		public static final int RES_BUSCAR_TODOS_SECCION_KO = 80;
		
		public static final int CREAR_VSECCION = 111;
		
		public static final int GUARDAR_SECCION = 133;

		public static final int UPDATE_SECCION = 134;
		
		// PRODUCTOS

		public static final int ALTA_PRODUCTO = 81;

		public static final int RES_ALTA_PRODUCTO_OK = 82;

		public static final int RES_ALTA_PRODUCTO_KO = 83;

		public static final int BAJA_PRODUCTO = 84;

		public static final int RES_BAJA_PRODUCTO_OK = 85;

		public static final int RES_BAJA_PRODUCTO_KO = 86;

		public static final int BUSCAR_PRODUCTO = 87;

		public static final int RES_BUSCAR_PRODUCTO_OK = 88;

		public static final int RES_BUSCAR_PRODUCTO_KO = 89;

		public static final int MODIFICAR_PRODUCTO = 90;

		public static final int RES_MODIFICAR_PRODUCTO_OK = 91;

		public static final int RES_MODIFICAR_PRODUCTO_KO = 92;

		public static final int BUSCAR_TODOS_PRODUCTO = 93;

		public static final int RES_BUSCAR_TODOS_PRODUCTO_OK = 94;

		public static final int RES_BUSCAR_TODOS_PRODUCTO_KO = 95;
		
		public static final int VINCULAR_PRODUCTO_PROVEEDOR = 96;
		
		public static final int GUARDAR_VINCULAR = 700;

		public static final int RES_VINCULAR_PRODUCTO_PROVEEDOR_OK = 97;

		public static final int RES_VINCULAR_PRODUCTO_PROVEEDOR_KO = 98;

		public static final int DESVINCULAR_PRODUCTO_PROVEEDOR = 99;

		public static final int RES_DESVINCULAR_PRODUCTO_PROVEEDOR_OK = 100;

		public static final int RES_DESVINCULAR_PRODUCTO_PROVEEDOR_KO = 101;

		public static final int BUSCAR_PRODUCTOS_PROVEEDOR_POR_RANGO_PRECIO = 102;

		public static final int RES_BUSCAR_PRODUCTOS_PROVEEDOR_POR_RANGO_PRECIO_OK = 103;

		public static final int RES_BUSCAR_PRODUCTOS_PROVEEDOR_POR_RANGO_PRECIO_KO = 104;

		public static final int UPDATE_PRODUCTO = 126;

		public static final int GUARDAR_PRODUCTO = 127;
		
		public static final int CREAR_VPRODUCTO = 112;

		
		// COMPRA

		public static final int ANIADIR_PRODUCTO_COMPRA = 105;

		public static final int RES_ANIADIR_PRODUCTO_COMPRA_OK = 567;

		public static final int RES_ANIADIR_PRODUCTO_COMPRA_KO = 354;

		public static final int ELIMINAR_PRODUCTO_COMPRA = 456;

		public static final int RES_ELIMINAR_PRODUCTO_COMPRA_OK = 603;

		public static final int RES_ELIMINAR_PRODUCTO_COMPRA_KO = 999;

		public static final int CERRAR_COMPRA = 31416;   //CERRAR COMPRA ->COMANDO DE CERRAR COMPRA

		public static final int RES_PAGAR_COMPRA_OK = 1121;

		public static final int RES_PAGAR_COMPRA_KO = 113;

	//	public static final int MOSTRAR_AYUDA = 137; 

		public static final int CREAR_VCOMPRA = 114;  // ABRIR COMPRA?
		
		public static final int CREAR_V_PAGAR_COMPRA = 140;
		
		public static final int STOCK_INSUFICIENTE = 142;
		
		public static final int PRECIO_PAGADO_INSUFICIENTE = 143;
		
		public static final int RES_BAJA_COMPRA_OK = 147;
		
		public static final int RES_BAJA_COMPRA_KO = 148;
		
		public static final int BUSCAR_COMPRA_ID = 149;

		public static final int RES_BUSCAR_COMPRA_ID_OK = 150;

		public static final int RES_BUSCAR_COMPRA_ID_KO = 151;
		
		public static final int BUSCAR_TODOS_COMPRA = 152;

		public static final int RES_BUSCAR_TODOS_COMPRA_OK = 153;

		public static final int RES_BUSCAR_TODOS_COMPRA_KO = 154;

		public static final int RES_DEVOLUCION_PRODUCTO_OK = 1515;

		public static final int RES_DEVOLUCION_PRODUCTO_KO = 1516;
		
		public static final int MOSTRAR_VINCULADOS = 600;
		
		public static final int BUSCAR_VINCULADOS = 601;
		
		public static final int RES_ABRIR_COMPRA_KO = 602;
		
		public static final int DEVOLUCION_COMPRA = 663;
		
		public static final int CREAR_V_DEVOLUCION = 604;
		
		public static final int VALIDAR_COMPRA=610;
		
		public static final int RES_ABRIR_COMPRA_OK = 611;


		// MAIN
		public static final int LOGIN = 1212;
		
		public static final int CREAR_VPRINCIPAL = 139;

		public static final int LOGOUT = 0;

		public static final int ABRIR_COMPRA = 141;

		public static final int VALTA_CLIENTE = 162;

		public static final int VMODIFICAR_CLIENTE = 163;

		public static final int VBAJA_CLIENTE = 165;

		public static final int VBUSCAR_CLIENTE = 166;

		public static final int VBUSCAR_SECCION = 167;
		
		public static final int VBAJA_SECCION = 168;
		
		public static final int VMODIFICAR_SECCION = 169;

		public static final int VALTA_SECCION = 170;
		
		public static final int VBUSCAR_CLIENTE_POR_DNI = 171;

		public static final int RES_BUSCAR_CLIENTE_POR_DNI_KO = 172;
		
		public static final int RES_BUSCAR_CLIENTE_POR_DNI_OK = 173;

		public static final int RES_BUSCAR_COMPRA_POR_PRECIO_FECHA_KO = 185;

		public static final int BUSCAR_COMPRA_POR_RANGO_PRECIO_FECHA = 187;

		public static final int VALIDAR_ID_CLIENTE = 19192 ;

		public static final int VALIDAR_COMPRA_SIN_CLIENTE = 28283;


}