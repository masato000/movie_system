package movie.model;

import movie.beans.LoginInfoBeans;
import movie.dao.UserDao;

public class LoginModel {

	/**
	 * ログイン処理を行う
	 *
	 * @param mail
	 * @param password
	 * @return null→ログイン失敗/非null→ログイン成功
	 */
	public LoginInfoBeans login(String studentId,String password){

		LoginInfoBeans login = null;

		/////////////////////////////////////
		//ここでDAOを呼び出して正しい
		//メールアドレスとパスワードかをチェックする

		UserDao userDao  = new UserDao();
		try{
			///////////////////////////////////
			//DBの接続
			userDao.connect();

			///////////////////////////////////
			//ユーザー情報の取得
			login = userDao.getBy(studentId, password);

		}catch(Exception e) {
			//エラー発生した場合にコンソールにログを出力する
			e.printStackTrace();
		}
		finally {
			//接続（コネクション）を閉じる
			if(userDao!=null) {
				userDao.close();
			}
		}

		return login;


		//////////////////////////////////////
		//【注意】↓のコードはとりあえず動くようにいれたダミーコードです。後で消してね！by先輩
		//login = new LoginInfoBeans();

		//login.setMail("dummy@mail");
		//login.setName("ダミー");

		//return login;

	}

}