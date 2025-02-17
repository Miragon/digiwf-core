/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik der Landeshauptstadt München, 2020
 */

package de.muenchen.oss.digiwf.process.instance.domain.service;

import de.muenchen.oss.digiwf.engine.mapper.EngineDataMapper;
import de.muenchen.oss.digiwf.json.serialization.JsonSerializationService;
import de.muenchen.oss.digiwf.jsonschema.domain.model.JsonSchema;
import de.muenchen.oss.digiwf.process.instance.process.ProcessConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service to query Data in service instances in DigiWF.
 *
 * @author externer.dl.horn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceInstanceDataService {

    private final JsonSerializationService serializationService;

    //Camunda Services
    private final HistoryService historyService;

    private final EngineDataMapper engineDataMapper;

    public Map<String, Object> getVariables(final String instanceId, final JsonSchema schema) {
        final Map<String, Object> typedVariables = this.historyService.createHistoricVariableInstanceQuery().executionIdIn(instanceId).processInstanceId(instanceId).list()
                .stream()
                .filter(obj -> obj.getValue() != null)
                .collect(Collectors.toMap(HistoricVariableInstance::getName, HistoricVariableInstance::getValue));
        return this.engineDataMapper.mapToData(this.serializationService.deserializeData(schema.getSchemaMap(), typedVariables));
    }

    public String getFileContext(final String instanceId) {
        return (String) this.historyService.createHistoricVariableInstanceQuery().executionIdIn(instanceId).processInstanceId(instanceId).variableName(ProcessConstants.PROCESS_FILE_CONTEXT).singleResult().getValue();
    }


}
