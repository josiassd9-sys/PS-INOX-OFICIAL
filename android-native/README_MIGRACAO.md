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

## 4.1. Consulta à Estrutura do Projeto Híbrido (PS-INOX)

Para cada nova página, menu, calculadora ou processo a ser migrado para o app nativo, é obrigatório registrar no histórico e na documentação a consulta à estrutura da pasta:

	PS C:\Users\josia\PS-INOX

Sempre que for iniciar a migração de uma nova funcionalidade, registre no README (ou no arquivo de status) a referência à consulta dessa pasta, detalhando quais arquivos, subpastas ou fluxos do projeto híbrido foram analisados. Isso facilita o rastreio do passo a passo da migração e garante transparência e entendimento do processo.

Exemplo de registro:

> [MIGRAÇÃO] Consulta realizada à pasta PS-INOX/src/app/perfis para implementar a tela de Perfis no nativo.

Inclua esse tipo de anotação para cada etapa relevante do processo de migração.

---


## 5. Inventário de Páginas, Menus e Calculadoras do Projeto Híbrido

### Páginas e Menus Principais
- Home (Dashboard)
- Perfis
	- Calculadora de Perfis
		- Armadura Sapata
		- Geometria
		- Laje
		- Pilar
		- Sapata
		- Viga Principal
		- Viga Secundária
		- Visualização
	- Guia de Perfis
	- Informações de Perfis
	- Parâmetros Vigas I
	- Tabela de Perfis
	- Tabela IPE
	- Tabela Steel Deck
- Materiais (Lista de Materiais)
- Sucatas
	- Lista de Sucatas
	- Tabela de Sucata
- Gauge
- Configurações Gerais
- Configurações de IA
- AI Assistant
- Informativos
	- Acabamentos Inox
	- Centro de Usinagem
	- Corte a Laser
	- Desenho Técnico
	- Início da Produção Industrial
	- Linha de Montagem Automotiva
	- Logística e Armazenamento
	- Normas ASTM
	- Processos de Fabricação
	- Processos Industriais Modernos
	- Tipos de Solda
- Mecânica
	- Elementos de Fixação
	- Sistemas de Vedação
	- Transmissão de Potência
- Calculadora
	- Balança
		- Impressão
		- Visualização
- Sobre

### Observações
- Este inventário foi levantado a partir da estrutura de pastas e arquivos do projeto híbrido (PS-INOX/src/app).
- Recomenda-se marcar cada item migrado e validar a equivalência funcional e visual no app nativo.
- Caso existam páginas dinâmicas, rotas de API ou integrações não listadas, incluir manualmente.

---
