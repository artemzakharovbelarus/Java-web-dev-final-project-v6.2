package by.epam.corporate_education.dao.util.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.exception.InvalidParameterTypeSortingException;
import by.epam.corporate_education.dao.util.api.SortingManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;

@Log4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SortingManagerImpl implements SortingManager {
    @Getter
    private static final SortingManager INSTANCE = new SortingManagerImpl();

    private final Map<String, String> ascMap = new HashMap<>();
    private final Map<String, String> descMap = new HashMap<>();
    private final Map<String, Map<String, String>> controller = new HashMap<>();

    @Override
    public String getSortingRequest(String parameter, String typeSorting) throws InvalidParameterTypeSortingException{
        init();
        String request = controller.get(typeSorting).get(parameter);
        if (request == null){
            String message = "No sorting request with parameter: " + parameter + ", type of sorting: " + typeSorting;
            log.error(message);
            throw new InvalidParameterTypeSortingException(message);
        }
        return request;
    }

    private void init(){
        /*
         * init ASC (DESC) controller
         */
        controller.put("desc", descMap);
        controller.put("asc", ascMap);

        /*
         * init ASC and DESC maps
         */
        String idUser = "idUser";
        ascMap.put(idUser, SQLRequest.GET_ALL_USERS_SORTED_BY_ID_ASC);
        descMap.put(idUser, SQLRequest.GET_ALL_USERS_SORTED_BY_ID_DESC);

        String idTraining = "idTraining";
        ascMap.put(idTraining, SQLRequest.GET_ALL_TRAININGS_SORTED_BY_ID_ASC);
        descMap.put(idTraining, SQLRequest.GET_ALL_TRAININGS_SORTED_BY_ID_DESC);
    }
}
