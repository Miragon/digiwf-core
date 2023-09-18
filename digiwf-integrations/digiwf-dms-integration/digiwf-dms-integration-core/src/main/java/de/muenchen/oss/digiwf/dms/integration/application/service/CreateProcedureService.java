package de.muenchen.oss.digiwf.dms.integration.application.service;

import de.muenchen.oss.digiwf.dms.integration.application.port.in.CreateProcedureUseCase;
import de.muenchen.oss.digiwf.dms.integration.application.port.out.ProcedureRepository;
import de.muenchen.oss.digiwf.dms.integration.domain.Procedure;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@RequiredArgsConstructor
public class CreateProcedureService implements CreateProcedureUseCase {

    private final ProcedureRepository procedureRepository;

    @Override
    public Procedure createProcedure(
            @NotBlank final String titel,
            @NotBlank final String fileCOO,
            @NotBlank final String user) {
        final Procedure procedure = new Procedure(fileCOO, titel);

        return procedureRepository.createProcedure(procedure, user);
    }
}
