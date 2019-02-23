package ${basePackage}.controller;

import com.cengel.hibernate.controller.BaseController;
import com.cengel.starbucks.model.obj.DataGrid;
import com.cengel.starbucks.model.obj.Page;
import com.cengel.starbucks.model.obj.Params;
import ${basePackage}.entity.${javacName};
import ${basePackage}.service.${javacName}Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("${javaName}")
public class ${javacName}Controller extends BaseController<${javacName}Service,${javacName}> {


    @RequestMapping("list")
    public String list(){
        return "${webPath}/${javaName}-list";
    }

    @RequestMapping("add")
    public String add(){
        return "${webPath}/${javaName}-add";
    }

    @RequestMapping("edit")
    public String edit(Integer id,ModelMap modelMap){
        modelMap.put("${javaName}",this.service.get(id));
        return "${webPath}/${javaName}-edit";
    }

    @RequestMapping("grid")
    @ResponseBody
    public Map<String, Object> grid(${javacName} entity, Page page){
        return DataGrid.renderDataGrid(this.service.pageLike(Params.create().addEntity(entity),Page.renderPage(page)));
    }


}
