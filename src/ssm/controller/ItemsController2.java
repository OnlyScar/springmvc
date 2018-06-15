package ssm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import ssm.po.Items;

/** 
 * @author  xiaomingHe
 * @date 创建时间：2018年4月17日 上午11:17:53 
 * @version 1.0 
 */
public class ItemsController2 implements HttpRequestHandler {
    
    @Override
    public void handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
      //实际中是调用service查找数据库，查询商品列表，这里直接使用静态数据来模拟了
        List<Items> itemsList = new ArrayList<Items>();
        //向list中填充静态数据
        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iPhone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);

        //设置模型数据
        arg0.setAttribute("itemsList", itemsList);

        //设置转发的视图
        arg0.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(arg0, arg1);

        //使用此方法可以通过修改response，设置响应的数据格式，比如响应json数据
        /*
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("json串");*/
        
    }
    
}
