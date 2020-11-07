package br.com.bct.cadastro.logger;

import br.com.bct.cadastro.model.constants.API;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class LoggerUtil {
    private LoggerUtil() {

    }

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public static void logger(String step, String message, String description, Object request,
                              Object response) {
        LoggerModelProperties loggerModelProperties = new LoggerModelProperties(API.NOME, step, message, description,
                request, response);
        getLogger(loggerModelProperties);
    }

    public static void logger(LoggerStepEnum loggerStepEnum) {
        logger(loggerStepEnum.name(), loggerStepEnum.getMessage(), "", "", "");
    }

    public static void logger(LoggerStepEnum loggerStepEnum, Object request) {
        logger(loggerStepEnum.name(), loggerStepEnum.getMessage(), "", request, "");
    }

    public static void logger(LoggerStepEnum loggerStepEnum, Object request, Object response) {
        logger(loggerStepEnum.name(), loggerStepEnum.getMessage(), "", request, response);
    }

    private static void getLogger(LoggerModelProperties loggerModelProperties) {
        LoggerModel loggerModel = new LoggerModel();
        loggerModel.setUsuario(loggerModelProperties);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.convertValue(loggerModel, JsonNode.class);

            String jsonFinal = mapper.writeValueAsString(jsonNode);

            logger.info(jsonFinal);

        } catch (JsonProcessingException e) {
            logger.error("ERROR AO GERAR LOG", e.getCause());
        }
    }

    public static void logger(LoggerModelProperties loggerModelProperties) {
        getLogger(loggerModelProperties);
    }
}
