package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ArticleDAO;
import model.ArticleDTO;
import model.UserDTO;

/**
 * Servlet implementation class ArticleEditServlet
 */
@WebServlet("/ArticleEditServlet")
public class ArticleEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

		String message = null;

		// ログインチェック
		if (loginUser == null) {
			message = "ログインしてください";
			request.setAttribute("message", message);
			// ログイン画面へ
			response.sendRedirect("LoginServlet");
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			ArticleDAO dao = new ArticleDAO();
			// 受け取ったIDの記事を取得
			List<ArticleDTO> articlelist = dao.searchArticle(id);

			request.setAttribute("articlelist", articlelist);
			String view = "/WEB-INF/view/article_edit.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
