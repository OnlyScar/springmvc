package ssm.service;

import java.util.List;

import ssm.po.Items;
import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;

/** 
 * @author  xiaomingHe
 * @date 创建时间：2018年4月17日 下午6:07:55 
 * @version 1.0 
 */
public interface ItemsService {
    
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
    
    public ItemsCustom findItemsById(Integer id) throws Exception;
    
    public Items findItemById(Integer id) throws Exception;

    public void updateItems(Integer id, ItemsCustom itemsCustom);
    
    
}
