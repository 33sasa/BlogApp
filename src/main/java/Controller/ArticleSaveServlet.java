package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ArticleDAO;
import model.UserDTO;

/**
 * Servlet implementation class ArticleSaveServlet
 */
@WebServlet("/ArticleSaveServlet")
public class ArticleSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String message;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleSaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

		request.setCharacterEncoding("UTF-8");

		// パラメータ受け取り、XSS対策
		String title = Xss.sanitizing(request.getParameter("title"));
		String body = Xss.sanitizing(request.getParameter("body"));

		message = null;

		// 入力チェック
		inputCheck(title, body);
		
		// エラー検出している場合
		if (message != null) {
			request.setAttribute("message", message);
			// 新規登録画面へ
			String view = "/WEB-INF/view/article_new.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			return;
		}

		// ログインチェック
		if (loginUser == null) {
			message = "ログインしてください";
			request.setAttribute("message", message);
			// ログイン画面へ
			response.sendRedirect("LoginServlet");
		} else {
			String user_name = loginUser.getName();
			ArticleDAO dao = new ArticleDAO();
			// 記事の登録
			dao.articleSave(title, body, user_name);

			// 記事一覧画面へ
			response.sendRedirect("ArticleListServlet");
		}

	}

	// 入力チェック
	public void inputCheck(String title, String body) {

		// 必須入力チェック
		if (!checkNull(title) || !checkNull(body)) {
			// エラーメッセージ
			message = "タイトルまたは本文を入力してください";
			return;
		}

		// nameの文字数チェック
		if (!checkLength(title, 15)) {
			// エラーメッセージ
			message = "タイトルは15文字以内です";
			return;
		}

		// passwordの文字数チェック
		if (!checkLength(body, 200)) {
			// エラーメッセージ
			message = "本文は200文字以内です";
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