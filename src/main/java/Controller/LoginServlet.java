package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	String message;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ログイン失敗を通知する
		request.setAttribute("ログインに失敗しました", "miss");

		// ログイン画面へ
		String view = "/WEB-INF/view/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// パラメータ受け取り、XSS対策
		String name = Xss.sanitizing(request.getParameter("name"));
		String password = Xss.sanitizing(request.getParameter("password"));

		message = null;

		// 入力チェック
		inputCheck(name, password);

		// エラー検出している場合
		if (message != null) {
			request.setAttribute("message", message);
			// ログイン画面へ
			String view = "/WEB-INF/view/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			return;
		}

		UserDAO userDAO = new UserDAO();
		UserDTO inputUser = new UserDTO(name, password);

		if (!userDAO.findbyLogin(inputUser)) {

			// エラーメッセージ
			message = "名前またはパスワードが間違っています";
			request.setAttribute("message", message);

			// ログイン画面へ
			String view = "/WEB-INF/view/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);

			dispatcher.forward(request, response);

		} else {

			// セッションに登録
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", inputUser);

			// 記事一覧画面へ
			String servlet = "ArticleListServlet";
			RequestDispatcher dispatcher = request.getRequestDispatcher(servlet);

			dispatcher.forward(request, response);
		}
	}

	// 入力チェック
	public void inputCheck(String name, String password) {

		// 必須入力チェック
		if (!checkNull(name) || !checkNull(password)) {
			// エラーメッセージ
			message = "名前またはパスワードを入力してください";
			return;
		}

		// nameの文字数チェック
		if (!checkLength(name, 10)) {
			// エラーメッセージ
			message = "名前は10文字以内です";
			return;
		}

		// passwordの文字数チェック
		if (!checkLength(password, 8)) {
			// エラーメッセージ
			message = "パスワードは8文字です";
			return;
		}
	}

	// string型の必須チェック
	public static boolean checkNull(String value) {
		if (value == null) {
			return false;

		}
		return true;
	}

	// string型の文字数チェック
	public static boolean checkLength(String value, int minimumLength) {
		if (value.length() > minimumLength) {
			return false;
		}
		return true;
	}
}
