# Observações e Próximos Passos – Migração Híbrido → Nativo

## Itens já migrados, testados e validados
- Todas as principais telas e calculadoras migradas (Home, Dashboard, Perfis, Materiais, Sucatas, Gauge, Configurações, Sobre)
- Navegação completa entre telas
- Persistência de dados implementada nas principais calculadoras
- Testes automatizados cobrindo fluxos principais e persistência
- Ajustes visuais e responsividade aplicados

## Itens migrados, pendentes de ajustes/testes finais
- Pilar, Visualização, AI Assistant, Informativos, Mecânica, Calculadora Balança
- Tabelas e guias secundários de perfis
- Integrações externas e incrementos de funcionalidades

## Próximos passos sugeridos
1. Finalizar ajustes e testes nas telas marcadas como [~] no checklist
2. Ampliar cobertura de testes automatizados para fluxos secundários e páginas informativas
3. Implementar incrementos: changelog, links úteis, gráficos no dashboard, integração de IA e preferências reais
4. Documentar cada nova etapa usando o TEMPLATE_REGISTRO_MIGRACAO.md
5. Validar equivalência funcional e visual com o app híbrido para todos os fluxos

## Observações Gerais
- Sempre registrar no MIGRACAO_STATUS.md cada avanço relevante
- Manter checklist atualizado para facilitar retomada e rastreio
- Priorizar não remover funcionalidades nem alterar fluxos já validados
