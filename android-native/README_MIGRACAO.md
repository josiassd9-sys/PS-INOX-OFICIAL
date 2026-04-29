# Plano de Migração: Projeto Híbrido → Android Nativo

## 1. Objetivo
Migrar todo o fluxo, lógica e visual do app híbrido (web/Capacitor) para um app Android nativo, utilizando Jetpack Compose, mantendo:
- Experiência visual idêntica ou superior
- Toda a lógica de negócio e filtros
- Responsividade e usabilidade mobile
- Estrutura de dados e funcionalidades principais

## 2. Etapas da Migração
### 2.1. Levantamento e Análise
- Mapear todas as telas, componentes e fluxos do app híbrido.
- Identificar regras de negócio, filtros, navegação e interações.
- Listar dados, fontes (mock/local/API) e estruturas usadas.

### 2.2. Estruturação do Projeto Nativo
- Criar projeto Android nativo com Jetpack Compose.
- Definir estrutura de pacotes (ex: screens, components, data, utils).
- Configurar dependências necessárias (Compose, Material, etc).

### 2.3. Portabilidade de Dados e Lógica
- Migrar os dados estáticos (ex: perfisData) para arquivos Kotlin (ex: objetos, classes ou JSON local).
- Reimplementar a lógica de filtros, buscas e cálculos em Kotlin, mantendo o mesmo comportamento do React.
- Garantir que toda a lógica de negócio seja testada e validada.

### 2.4. Conversão de Telas e Componentes
- Recriar cada tela do app híbrido como uma Composable no Jetpack Compose.
- Reproduzir o layout visual (cards, tabelas, filtros, menus) usando os componentes nativos do Compose.
- Implementar tabelas com cabeçalho fixo, colunas fixas e rolagem suave, similar ao efeito do web.
- Adaptar responsividade para diferentes tamanhos de tela Android.

### 2.5. Navegação e Fluxo
- Implementar navegação entre telas usando o Navigation Compose.
- Garantir que o fluxo do usuário seja igual ao do app híbrido.

### 2.6. Recursos e Integrações
- Migrar ícones, imagens e assets para o formato Android.
- Adaptar chamadas de API ou integrações externas, se houver.

### 2.7. Testes e Validação
- Testar cada tela e fluxo no emulador e dispositivos reais.
- Validar performance, responsividade e ausência de bugs.
- Garantir que todas as funcionalidades do híbrido estejam presentes e funcionando.

### 2.8. Documentação e Manutenção
- Documentar o código e as decisões de arquitetura.
- Deixar claro no README do projeto nativo o objetivo da migração e o que foi portado.

## 3. O que será mantido e o que será adaptado
- **Mantido:** Lógica de filtros, estrutura de dados, visual das telas, experiência do usuário, nomes e funções dos componentes.
- **Adaptado:** Sintaxe (de React/JS para Kotlin/Compose), navegação (React Router para Navigation Compose), assets para formato Android, eventuais APIs para uso nativo.

## 4. Observações Importantes
- Nenhuma funcionalidade será removida, apenas reimplementada em nativo.
- O visual será o mais fiel possível ao original, aproveitando recursos nativos para melhor performance.
- O código será modular, facilitando manutenção e evolução futura.
- Todo o processo será incremental, validando cada etapa para evitar regressão.

---

## Resumo
Vamos transformar o app híbrido em um app Android nativo, mantendo toda a lógica, visual e experiência, reescrevendo cada parte em Jetpack Compose/Kotlin, com foco em fidelidade, performance e manutenção.
