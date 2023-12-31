package dev.erikmota.desafiounikamain.service.validacoes;

import dev.erikmota.desafiounikamain.models.Monitorador;
import dev.erikmota.desafiounikamain.models.TipoPessoa;
import dev.erikmota.desafiounikamain.repository.MonitoradorRepository;
import dev.erikmota.desafiounikamain.service.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoCpfExistente implements IValidacaoMonitorador {

    @Autowired
    private MonitoradorRepository repository;

    @Override
    public void validar(Monitorador m) {
        if (m.getTipoPessoa() == TipoPessoa.FISICA) {
            if (m.getCpf() == null || m.getCpf().isBlank())
                throw new ValidacaoException("Pessoas físicas devem inserir cpf!");

            else if (repository.existsByCpf(m.getCpf().replaceAll("[^0-9]", ""))) {
                throw new ValidacaoException("Esse Cpf já está cadastrado!");
            }
        }
    }
}
