package com.fengwenyi.codegenerator.service;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.codegenerator.vo.CodeGeneratorRequestParam;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-12
 */
public interface IIndexService {

    /**
     * ็ๆไปฃ็ 
     * @param requestVo
     * @return
     */
    ResultTemplate<Void> codeGenerator(CodeGeneratorRequestParam requestVo);

}
