package movie.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoBase {

	protected Connection con = null;

	/**
	 * DB接続を行う
	 *
	 * @throws Exception　接続失敗（本来であれば自作の例外を飛ばす方が良い）
	 */
	public void connect() throws Exception{

		if( con != null ){
			//すでに接続済みの場合は何もしない
			return;
		}

		InitialContext context = null;

		try{
			///////////////////////////////////
			//DBの接続
			String resourceName = "jdbc/MySQL";
			String jndi = "java:comp/env/"+resourceName;

			context = new InitialContext();

			DataSource dataSource = (DataSource) context.lookup(jndi);

			con = dataSource.getConnection();

		}catch(NamingException e) {
			//エラー発生した場合にコンソールにログを出力する
			e.printStackTrace();
			throw e;
		}catch(SQLException e) {
			//エラー発生した場合にコンソールにログを出力する
			e.printStackTrace();
			throw e;
		}
	}


	/**
	 * 接続のクローズ処理
	 */
	public void close(){

		if( con != null ){
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				//クローズの失敗は、ログを出力するだけ
				System.out.println("closeに失敗しました");
			}
		}
	}

}

