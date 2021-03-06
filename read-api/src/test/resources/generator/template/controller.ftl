package ${basePackage}.${bussPackage}.web;
import ${basePackage}.common.core.Result;
import ${basePackage}.common.core.ResultGenerator;
import ${basePackage}.${bussPackage}.model.${modelNameUpperCamel};
import ${basePackage}.${bussPackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ${basePackage}.model.dto.ResultDTO;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("/backend/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public ResultDTO add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public ResultDTO delete(@RequestParam Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public ResultDTO update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public ResultDTO detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @PostMapping("/list")
    public ResultDTO list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,${modelNameUpperCamel} ${modelNameLowerCamel}) {
        PageHelper.startPage(page, size);

        Condition condition = new Condition(${modelNameUpperCamel}.class);
        Example.Criteria criteria = condition.createCriteria();

        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
