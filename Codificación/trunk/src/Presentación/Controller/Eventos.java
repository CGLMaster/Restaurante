package Presentación.Controller;

/*
 * Nomenclatura utilizada:
 * ALTA_*** = evento para generar la vista de Alta (Usado por la factoría de vistas)
 * GUARDAR_*** = Caso de uso de Alta (Usado por la factoría de comandos)
 * MODIFICAR_*** = evento para generar la vista de Modificar (Usado por la factoría de vistas)
 * UPDATE_*** = Caso de uso de Modificar (Usado por la factoría de comandos)
 * ELIMINAR_*** = Caso de uso de Eliminar (Usado por la factoría de comando)
 * BUSCAR_TODOS_*** = Caso de uso de Buscar todos para generar la vista principal (Usado por la factoría de comandos). Cuando se obtiene la lista de todos los objetos se llama a CREAR_V***
 * CREAR_V*** = evento para generar la vista principal (Usado por la factoría de vistas)
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

	public static final int CERRAR_COMPRA = 31416; //CERRAR COMPRA ->COMANDO DE CERRAR COMPRA

	public static final int RES_PAGAR_COMPRA_OK = 1698;

	public static final int RES_PAGAR_COMPRA_KO = 113;

	public static final int CREAR_VCOMPRA = 114; // ABRIR COMPRA?

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

	public static final int VALIDAR_COMPRA = 610;

	public static final int RES_ABRIR_COMPRA_OK = 611;

	// MAIN
	public static final int LOGIN = 1212;
	
	public static final int LOGIN_REST = 3845;
	
	public static final int CREAR_VPRINCIPAL = 1800;

	public static final int CREAR_VPRINCIPAL_SUPER = 139;
	
	public static final int CREAR_VPRINCIPAL_REST = 998;

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

	public static final int VALIDAR_ID_CLIENTE = 19192;

	public static final int VALIDAR_COMPRA_SIN_CLIENTE = 28283;
	
	//Eventos Restaurante.
	
	//ClienteRest [1000 - 1050]
	
	public static final int ALTA_CLIENTE_REST = 1001;

	public static final int RES_ALTA_CLIENTE_REST_OK = 1002;

	public static final int RES_ALTA_CLIENTE_REST_KO = 1003;

	public static final int BAJA_CLIENTE_REST = 1004;

	public static final int RES_BAJA_CLIENTE_REST_OK = 1005;

	public static final int RES_BAJA_CLIENTE_REST_KO = 1006;

	public static final int BUSCAR_CLIENTE_REST = 1007;

	public static final int RES_BUSCAR_CLIENTE_REST_OK = 1008;

	public static final int RES_BUSCAR_CLIENTE_REST_KO = 1009;

	public static final int MODIFICAR_CLIENTE_REST = 1010;

	public static final int RES_MODIFICAR_CLIENTE_REST_OK = 1011;

	public static final int RES_MODIFICAR_CLIENTE_REST_KO = 1012;

	public static final int BUSCAR_TODOS_CLIENTE_REST = 1013;

	public static final int RES_BUSCAR_TODOS_CLIENTE_REST_OK = 1014;

	public static final int RES_BUSCAR_TODOS_CLIENTE_REST_KO = 1015;

	public static final int CREAR_VCLIENTE_REST = 1016;

	public static final int GUARDAR_CLIENTE_REST = 1017;

	public static final int UPDATE_CLIENTE_REST = 1018;
	
	public static final int VALTA_CLIENTE_REST = 1019;
	
	public static final int VMODIFICAR_CLIENTE_REST = 1020;
	
	
	//Ingredientes [1051 - 1100]
	public static final int RES_ALTA_INGREDIENTE_KO = 1051;
	
	public static final int RES_ALTA_INGREDIENTE_OK = 1052;
	
	public static final int RES_BAJA_INGREDIENTE_KO = 1053;
	
	public static final int RES_BAJA_INGREDIENTE_OK = 1054;
	
	public static final int CREAR_VINGREDIENTE = 1055;
	
	public static final int BUSCAR_TODOS_INGREDIENTE=1056;
	
	public static final int RES_BUSCAR_TODOS_INGREDIENTE_KO = 1057;
	
	public static final int BUSCAR_INGREDIENTE = 1058;
	
	public static final int RES_BUSCAR_INGREDIENTE_KO = 1059;
	
	public static final int RES_MODIFICAR_INGREDIENTE_OK = 1060;
	
	public static final int RES_MODIFICAR_INGREDIENTE_KO = 1061;
	
	public static final int ALTA_INGREDIENTE = 1062;
	
	public static final int GUARDAR_INGREDIENTE = 1063;
	
	public static final int MODIFICAR_INGREDIENTE = 1064;
	
	public static final int UPDATE_INGREDIENTE = 1065;
	
	public static final int BAJA_INGREDIENTE = 1066;
	
	//Turno [1101 - 1150]
	public static final int RES_ALTA_TURNO_KO = 1101;
	
	public static final int RES_ALTA_TURNO_OK = 1102;
	
	public static final int RES_BAJA_TURNO_KO = 1103;
	
	public static final int RES_BAJA_TURNO_OK = 1104;
	
	public static final int CREAR_VTURNO = 1105;
	
	public static final int RES_BUSCAR_TODOS_TURNO_KO = 1106;
	
	public static final int BUSCAR_TURNO = 1107;
	
	public static final int RES_BUSCAR_TURNO_KO = 1108;
	
	public static final int RES_MODIFICAR_TURNO_OK = 1109;
	
	public static final int RES_MODIFICAR_TURNO_KO = 1110;
	
	public static final int RES_CALCULAR_NOMINA_OK = 1111;
	
	public static final int RES_CALCULAR_NOMINA_KO = 1112;
	
	public static final int ALTA_TURNO = 1113;
	
	public static final int GUARDAR_TURNO = 1114;
	
	public static final int MODIFICAR_TURNO = 1115;
	
	public static final int UPDATE_TURNO = 1116;
	
	public static final int CALCULAR_NOMINA_TURNO = 1117;
	
	public static final int BAJA_TURNO = 1118;
	
	public static final int BUSCAR_TODOS_TURNO = 1119;
	
	//Personal [1151 - 1200]
	
	public static final int ALTA_PERSONAL = 1151;

	public static final int RES_ALTA_PERSONAL_OK = 1152;

	public static final int RES_ALTA_PERSONAL_KO = 1153;

	public static final int BAJA_PERSONAL = 1154;

	public static final int RES_BAJA_PERSONAL_OK = 1155;

	public static final int RES_BAJA_PERSONAL_KO = 1156;

	public static final int BUSCAR_PERSONAL = 1157;

	public static final int RES_BUSCAR_PERSONAL_OK = 1158;

	public static final int RES_BUSCAR_PERSONAL_KO = 1159;

	public static final int MODIFICAR_PERSONAL = 1160;

	public static final int RES_MODIFICAR_PERSONAL_OK = 1161;

	public static final int RES_MODIFICAR_PERSONAL_KO = 1162;

	public static final int BUSCAR_TODOS_PERSONAL = 1163;

	public static final int RES_BUSCAR_TODOS_PERSONAL_OK = 1164;

	public static final int RES_BUSCAR_TODOS_PERSONAL_KO = 1165;
	
	public static final int IDENTIFICAR_PERSONAL = 1166;

	public static final int RES_IDENTIFICAR_PERSONAL_OK = 1167;

	public static final int RES_IDENTIFICAR_PERSONAL_KO = 1168;
	
	public static final int BUSCAR_PERSONAL_DNI_PERSONAL = 1169;

	public static final int RES_BUSCAR_PERSONAL_DNI_OK = 1170;

	public static final int RES_BUSCAR_PERSONAL_DNI_KO = 1171;

	public static final int CREAR_VPERSONAL = 1172;
	
	public static final int VALTA_PERSONAL = 1173;
	
	public static final int VMODIFICAR_PERSONAL = 1174;
	
	public static final int BUSCAR_PERSONAL_DNI = 1175;
	
	//Plato [1201 - 1250]
	public static final int RES_ALTA_PLATO_KO = 1201;
	
	public static final int RES_ALTA_PLATO_OK = 1202;
	
	public static final int RES_BAJA_PLATO_KO = 1203;
	
	public static final int RES_BAJA_PLATO_OK = 1204;
	
	public static final int CREAR_VPLATO = 1205;
	
	public static final int BUSCAR_TODOS_PLATO=1206;
	
	public static final int RES_BUSCAR_TODOS_PLATO_KO = 1207;
	
	public static final int BUSCAR_PLATO = 1208;
	
	public static final int RES_BUSCAR_PLATO_KO = 1209;
	
	public static final int RES_MODIFICAR_PLATO_OK = 1210;
	
	public static final int RES_MODIFICAR_PLATO_KO = 1211;
	
	public static final int ALTA_PLATO = 99999;
	
	public static final int GUARDAR_PLATO = 1213;
	
	public static final int MODIFICAR_PLATO = 1214;
	
	public static final int UPDATE_PLATO = 1215;
	
	public static final int BAJA_PLATO = 1216;
	
	public static final int BUSCAR_PLATO_CON_INGREDIENTES = 1217;
	
	public static final int ANIADIR_INGREDIENTE_A_PLATO = 1218;
	
	public static final int RES_ANIADIR_INGREDIENTE_A_PLATO_KO = 1219;
	
	public static final int RES_ANIADIR_INGREDIENTE_A_PLATO_OK = 1220;
	
	public static final int ELIMINAR_INGREDIENTE_DE_PLATO = 1221;
	
	public static final int RES_ELIMINAR_INGREDIENTE_DE_PLATO_KO = 1222;
	
	public static final int RES_ELIMINAR_INGREDIENTE_DE_PLATO_OK = 1223;

	
	//Pedido [1251 - 1300]
	public static final int DEVOLUCION_PEDIDO = 1251;
	
	public static final int CREAR_V_DEVOLUCION_PEDIDO = 1252;
	
	public static final int BUSCAR_TODOS_PEDIDO = 1253;
	
	public static final int CREAR_VPEDIDO = 1254;
	
	public static final int BUSCAR_PEDIDO = 1255;
	
	public static final int ALTA_PEDIDO = 1256;
	
	public static final int MODIFICAR_PEDIDO = 1257;
	
	public static final int BAJA_PEDIDO = 1258;
	
	public static final int RES_BAJA_PEDIDO_OK = 1259;
	
	public static final int RES_BAJA_PEDIDO_KO = 1260;
	
	public static final int RES_BUSCAR_PEDIDO_KO = 1261;
	
	public static final int RES_BUSCAR_TODOS_PEDIDO_KO = 1262;
	
	public static final int CREAR_CARRITO=1263;			
	
	public static final int CREAR_CARRITO_OK=1264;
	
	public static final int CREAR_CARRITO_KO=1265;
	
	public static final int CREAR_VCARRITO=1266;		
	
	public static final int ANIADIR_PLATO_PEDIDO=1267;
	
	public static final int ANIADIR_PLATO_PEDIDO_OK=1268;
	
	public static final int ANIADIR_PLATO_PEDIDO_KO=1269;
	
	public static final int ELIMINAR_PLATO_PEDIDO=1270;
	
	public static final int ELIMINAR_PLATO_PEDIDO_OK=1271;
	
	public static final int ELIMINAR_PLATO_PEDIDO_KO=1272;
	
	public static final int VALIDAR_PEDIDO_SIN_CLIENTE = 1273;
	
	public static final int RES_ABRIR_PEDIDO_KO = 1274;

	public static final int RES_ABRIR_PEDIDO_OK = 1275;
	
	public static final int V_CERRAR_PEDIDO = 1276;
	
	public static final int CREAR_V_DESPEDIDA_PEDIDO=1277;

	public static final int CERRAR_PEDIDO = 1278;

	public static final int RES_PAGAR_PEDIDO_OK = 1279;
	
	public static final int RES_PAGAR_PEDIDO_KO = 1280;
	
	public static final int RES_DEVOLUCION_PLATOS_OK = 1281;
	
	public static final int RES_DEVOLUCION_PLATOS_KO = 1282;

	public static final int BUSCAR_PEDIDOS_POR_CLIENTE = 1283;
	


	

}