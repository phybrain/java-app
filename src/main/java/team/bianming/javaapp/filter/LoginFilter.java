//package team.bianming.javaapp.filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * Created by ckwin8 on 2017/10/18.
// */
//public class LoginFilter implements Filter {
//
//    @Override
//    public void destroy() {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request2 = (HttpServletRequest)request;
//
////        XssAndSqlHttpServletRequestWrapper xssRequest = new XssAndSqlHttpServletRequestWrapper((HttpServletRequest) request);
//        if("".equals(request2.getSession().getAttribute("type"))){
//            request2.getSession().setAttribute("type","userid");
//            request2.getSession().setAttribute("userid",1);
//        }
//        chain.doFilter(request2, response);
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//        // TODO Auto-generated method stub
//
//    }
//
//}
