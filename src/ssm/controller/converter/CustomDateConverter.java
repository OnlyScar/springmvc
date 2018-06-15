package ssm.controller.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/** 
 * @author  xiaomingHe
 * @date 创建时间：2018年4月19日 下午2:07:23 
 * @version 1.0 
 */
public class CustomDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        //实现 将日期串转成日期类型（格式是yyyy-MM-dd HH:mm:ss)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(source);
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        //如果参数绑定失败返回null
        return null;
    }
    
}
