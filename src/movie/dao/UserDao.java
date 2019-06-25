package movie.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import movie.beans.LoginInfoBeans;

public class UserDao extends DaoBase{

	/////////////////////////////////////
	//検索と挿入のメソッドが必要だお思うよ。
	//
	//検索は、メールアドレスとパスワードを引数で受け取ってユーザーテーブルから
	//値を取得するようなメソッドじゃないかな
	//
	//挿入は、引数で受け取った情報をDBに登録するメソッドじゃないかな
	// by 先輩

	/**
	 * ユーザーDBよりユーザー情報を取得する
	 * （ログイン処理と等価）
	 *
	 * @param mail　メールアドレス
	 * @param password　パスワード
	 * @return
	 */
	public LoginInfoBeans getBy(String studentID,String password){

		if( con == null){
			//接続していない場合はエラーとする
			return null;
		}
		LoginInfoBeans loginInfo = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try{

			///////////////////////////////////
			//SELECT文の発行

			stmt = con.prepareStatement("SELECT * FROM student,class,course WHERE student.class_code=class.class_code and class.course_code=course.course_code and student_id=? AND student_password=?");
			stmt.setString(1, studentID);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			///////////////////////////////////
			//DBから値を取得
			while( rs.next() ){
				loginInfo = new LoginInfoBeans();


				loginInfo.setStudentID(rs.getString("student_id"));
				loginInfo.setName(rs.getString("student_name"));
				loginInfo.setClassCode(rs.getString("class_code"));
				loginInfo.setClassName(rs.getString("class_name"));
				loginInfo.setGrade(rs.getInt("grade"));
				loginInfo.setCourse(rs.getString("course_name"));

			}

		}catch(SQLException e) {
			//エラー発生した場合にコンソールにログを出力する
			e.printStackTrace();
		}
		finally {
	    	try {
		        // 接続を閉じる
	        	if( rs != null ){
					rs.close();
	        	}
	        	if( stmt != null ){
	        		stmt.close();
	        	}
			} catch (SQLException e) {
				System.out.println("closeに失敗しました");
				;	//closeの失敗は無視
			}
		}

		return loginInfo;


	}


}
