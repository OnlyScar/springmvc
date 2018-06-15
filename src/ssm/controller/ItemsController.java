package ssm.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;
import ssm.service.ItemsService;


/** 
 * @author  xiaomingHe
 * @date 创建时间：2018年4月17日 下午6:26:43 
 * @version 1.0 
 */
@Controller
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    
    @RequestMapping("queryItems")
    public ModelAndView queryItems() throws Exception{
        //调用service查找数据库，查询商品列表
        //这里传入进去一个null表示没有附加条件，查询所有的。因为service中接收的是一个ItemsQueryVo对象
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);
        
        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
                
        return modelAndView;
        
    }
    
    @RequestMapping("/editItems")
    public String editItems(Model model,@RequestParam(value="id",required=true)Integer items_id)
            throws Exception {
        // 根据id查询对应的Items
        ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
  
        
        model.addAttribute("itemsCustom", itemsCustom);

        return "/WEB-INF/jsp/items/editItems.jsp";
    }
    
   /* @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom) throws Exception {
        itemsService.updateItems(id,itemsCustom);
        return "/WEB-INF/jsp/success.jsp";     
    }*/
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Model model, HttpServletRequest request,
            Integer id,
            @Validated ItemsCustom itemsCustom,
            BindingResult bindingResult
            )
            throws Exception {

        // 获取校验错误信息
        if (bindingResult.hasErrors()) {
            // 输出错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                // System.out.println(objectError.getDefaultMessage());
                // 原来是上面这句，但是由于properties文件默认无法输入中文，所以我把properties文件改成了utf-8编码，
                // 但是这样的话读取出来就是乱码了，所以我先用iso打乱，再用utf-8生成，即可解决乱码问题
                System.out.println(new String(objectError.getDefaultMessage()
                        .getBytes("ISO-8859-1"), "UTF-8"));
            }
            // 将错误信息传到页面
            model.addAttribute("allErrors", allErrors);
            return "/WEB-INF/jsp/items/editItems.jsp";
        }      
        //更新数据库
        itemsService.updateItems(id, itemsCustom);

        return "/WEB-INF/jsp/success.jsp";
    }

    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(Model model, ItemsQueryVo itemsQueryVo)
            throws Exception {

        // 打个断点，即可查看itemsQueryVo中itemsCustom是否已经接收到了前面传来的name
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
        model.addAttribute("itemsList", itemsList);
        return "/WEB-INF/jsp/items/itemsList.jsp";
    }

    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception {

        // 这里就不删了，因为数据我们后面还需要，在return前打个断点，看一下items_id中的值即可
        return "/WEB-INF/jsp/success.jsp";
    }
    
}

