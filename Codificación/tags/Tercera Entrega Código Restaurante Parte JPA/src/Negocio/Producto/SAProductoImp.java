package Negocio.Producto;

import java.util.HashMap;
import java.util.List;

import Integración.FactoriaIntegracion.FactoriaIntegracion;
import Integración.FactoriaQueries.FactoriaQueries;
import Negocio.Marca.TMarca;
import Negocio.Proveedor.TProveedor;
import Negocio.Seccion.TSeccion;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;
import Integración.Producto.DAODistribuye;
import Integración.Producto.DAOProducto;
import Integración.Proveedor.DAOProveedor;
import Integración.Queries.Query;

public class SAProductoImp implements SAProducto {

	public int altaProducto(TProducto prod) {
		int res = -1;
		try {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			TMarca marca = FactoriaIntegracion.getInstance().getDAOMarca().buscarMarca(prod.getIDMarca());
			TSeccion secc = FactoriaIntegracion.getInstance().getDAOSeccion().buscarSeccion(prod.getIDSeccion());

			if ((marca != null && marca.getActivo()) && (secc != null && secc.getActivo())) {
				TProducto prod_exist = FactoriaIntegracion.getInstance().getDAOProducto()
						.buscarProductoPorMarcayNombre(prod.getNombre(), prod.getIDMarca());
				if (prod_exist == null) {
					marca.setContProductos(marca.getContProductos() + 1);
					secc.setCont_productos(secc.getCont_productos() + 1);
					int update = 0;
					update += FactoriaIntegracion.getInstance().getDAOMarca().modificarMarca(marca);
					update += FactoriaIntegracion.getInstance().getDAOSeccion().modificarSeccion(secc);
					res = FactoriaIntegracion.getInstance().getDAOProducto().altaProducto(prod);
					if (res > 0 && update == 2) {
						t.commit();
					}
				} else {
					t.rollback();
					throw new Exception();
				}
			} else {
				t.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			res = -1;
			e.printStackTrace();
		}
		return res;

	}

	public int bajaProducto(int prod) {
		int res = 0;
		try {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			TProducto prod_exist = FactoriaIntegracion.getInstance().getDAOProducto()
					.buscarProducto(prod);
			List<TDistribuye> listaDistr = FactoriaIntegracion.getInstance().getDAODistribuye().buscarTodosVinculosProducto(prod);
			if (prod_exist != null && listaDistr.size() == 0) {
				res = FactoriaIntegracion.getInstance().getDAOProducto().bajaProducto(prod);
				t.commit();
			} else {
				t.rollback();
				res = -1;
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int modificarProducto(TProducto prod) {
		try {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOProducto dao = FactoriaIntegracion.getInstance().getDAOProducto();
			TProducto p = dao.buscarProducto(prod.getIDProducto());
			if (p != null) {
				TProducto aux = null;
				int id_marca = 0;
				int id_secc = 0;
				if(!prod.getNombre().equals(p.getNombre())){
					aux = dao.buscarProductoPorMarcayNombre(prod.getNombre(), prod.getIDMarca());
				}
				if(prod.getIDSeccion() != p.getIDSeccion()){
					TSeccion secc = FactoriaIntegracion.getInstance().getDAOSeccion().buscarSeccion(prod.getIDSeccion());
					if(secc!= null && secc.getActivo()){
						id_secc = secc.getID_Seccion();
					}else{
						id_secc = -1;
					}
				}
				
				if(prod.getIDMarca() != p.getIDMarca()){
					TMarca marca = FactoriaIntegracion.getInstance().getDAOMarca().buscarMarca(prod.getIDMarca());
					if(marca!= null && marca.getActivo()){
						id_marca = marca.getID();
					}else{
						id_marca = -1;
					}
				}
				
				if (aux == null  && id_secc > -1 && id_marca > -1) {
					int res = dao.modificarProducto(prod);
					if (res > 0)
						t.commit();
					else {
						t.rollback();
						throw new Exception();
					}
				} else {
					t.rollback();
					throw new Exception();
				}
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public TProducto buscarProducto(int id) {
		return FactoriaIntegracion.getInstance().getDAOProducto().buscarProducto(id);
	}

	public List<TProducto> buscarTodosProducto() {
		return FactoriaIntegracion.getInstance().getDAOProducto().buscarTodosProducto();
	}

	public int vincularProveedor(TDistribuye distribuye) { 
		try {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOProducto daoP = FactoriaIntegracion.getInstance().getDAOProducto();
			DAOProveedor daoPR = FactoriaIntegracion.getInstance().getDAOProveedor();
			DAODistribuye daoD = FactoriaIntegracion.getInstance().getDAODistribuye();

			TProducto prod = daoP.buscarProducto(distribuye.getIdProducto());
			if (prod != null) {
				TProveedor prov = daoPR.buscarProveedor(distribuye.getIdProveedor());
				if (prod.getActivo() && prov != null) {
					TDistribuye d = daoD.buscarVinculoProducto(distribuye.getIdProducto(), distribuye.getIdProveedor());
					if (prov.getActivo() && d == null) {
						int res = daoD.vincularProductoProveedor(distribuye);
						t.commit();
						return res;
					}
				}
			}
			t.rollback();
			throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int desvincularProveedor(TDistribuye distribuye) {
		try {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOProducto daoP = FactoriaIntegracion.getInstance().getDAOProducto();
			DAOProveedor daoPR = FactoriaIntegracion.getInstance().getDAOProveedor();
			DAODistribuye daoD = FactoriaIntegracion.getInstance().getDAODistribuye();

			TProducto prod = daoP.buscarProducto(distribuye.getIdProducto());
			if (prod != null) {
				TProveedor prov = daoPR.buscarProveedor(distribuye.getIdProveedor());
				if (prod.getActivo() && prov != null) {
					TDistribuye d = daoD.buscarVinculoProducto(distribuye.getIdProducto(), distribuye.getIdProveedor());
					if (prov.getActivo() && d != null) {
						int res = daoD.desvincularProductoProveedor(distribuye);
						t.commit();
						return res;
					}
				}
			}
			
			t.rollback();
			throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<TProducto> buscarProductosPorRangoPrecio(int desde, int hasta) {
		try {
			DAOProducto daoP = FactoriaIntegracion.getInstance().getDAOProducto();
			if (desde >= 0 && desde < hasta) {
				Query q = FactoriaQueries.getInstance().getNewQuery("ProductosPorRangoPrecio");
				HashMap<String, Object> args = new HashMap<String, Object>();
				args.put("desde", desde);
				args.put("hasta", hasta);
				return (List<TProducto>) q.Execute(args);
			}
			throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TDistribuye> buscarVinculadosAProd(int id_producto) {
		try {
			DAOProducto daoP = FactoriaIntegracion.getInstance().getDAOProducto();
			DAODistribuye daoD = FactoriaIntegracion.getInstance().getDAODistribuye();

			TProducto prod = daoP.buscarProducto(id_producto);
			if (prod != null) {
				if (prod.getActivo()) {
					List<TDistribuye> res = daoD.buscarTodosVinculosProducto(id_producto);
					return res;
				}
			}

			throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}