/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleatleta.ui;

import controleatleta.controlador.ControleJogadorBasquete;
import controleatleta.modelo.Partida;
import controleatleta.modelo.Endereco;
import controleatleta.modelo.JogadorBasquete;
import controleatleta.modelo.Posicao;
import controleatleta.modelo.Premiacao;
import controleatleta.modelo.Time;
import controleatleta.util.FabricaMaskFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author nanson
 */
public class CadastroJogadorBasquete extends javax.swing.JFrame {

    private final byte SEXO_MASCULINO_INDICE = 0;
    private final byte SEXO_FEMININO_INDICE = 1;
    private final char SEXO_MASCULINO_VALOR = 'M';
    private final char SEXO_FEMININO_VALOR = 'F';
    private final byte CATEGORIA_AMADOR_INDICE = 0;
    private final byte CATEGORIA_PROFISSIONAL_INDICE = 1;
    private final char CATEGORIA_AMADOR_VALOR = 'A';
    private final char CATEGORIA_PROFISSIONAL_VALOR = 'P';
    private Map<Posicao, Integer> mapPosicao;
    private Map<Integer, Posicao> mapPosicaoInversa;
    private final Posicao POSICAO_ALA = Posicao.ALA;
    private final Posicao POSICAO_ALA_ARMADOR = Posicao.ALA_ARMADOR;
    private final Posicao POSICAO_PIVO = Posicao.PIVO;
    private final Posicao POSICAO_ARMADOR = Posicao.PIVO;
    private final Posicao POSICAO_ALA_PIVO = Posicao.ALA_PIVO;
    private boolean modoAlteracao;
    private boolean novoRegistro;
    private DefaultListModel telefonesListModel;
    private DefaultListModel partidasListModel;
    private DefaultListModel timesListModel;
    private DefaultListModel premiacaoListModel;
    private DateFormat dateFormat;
    private MaskFormatter ftmData;
    private MaskFormatter ftmCPF;
    private MaskFormatter ftmCEP;
    private JogadorBasquete jogador;
    private ControleJogadorBasquete controleJogador;

    public CadastroJogadorBasquete() {

        try {
            ftmData = FabricaMaskFormatter.getData();;
            ftmCPF = FabricaMaskFormatter.getCPF();
            ftmCEP = FabricaMaskFormatter.getCEP();
        } catch (ParseException ex) {
            Logger.getLogger(CadastroJogadorBasquete.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();

        this.habilitarDesabilitarCampos();

        this.controleJogador = new ControleJogadorBasquete();

        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        this.premiacaoListModel = new DefaultListModel();
        this.jListPremiacao.setModel(premiacaoListModel);

        this.telefonesListModel = new DefaultListModel();
        this.jListTelefones.setModel(telefonesListModel);

        this.partidasListModel = new DefaultListModel();
        this.jListPartidas.setModel(partidasListModel);

        this.timesListModel = new DefaultListModel();
        this.jListTimes.setModel(timesListModel);


        limparCampos();
        inicializarMapPosicao();

    }

    private void inicializarMapPosicao() {
        this.mapPosicao = new EnumMap<Posicao, Integer>(Posicao.class);
        this.mapPosicaoInversa = new HashMap<Integer, Posicao>();

        int temp = 0;

        for (Posicao valor : Posicao.values()) {
            mapPosicao.put(valor, temp);
            mapPosicaoInversa.put(temp, valor); // sem paciencia para procurar a chave 
            temp++;
        }
    }

    // ctrl c + ctrl v
    private void limparCampos() {
        jTextFieldAltura.setText("0.0");
        jTextFieldBairro.setText(null);
        jFormattedTextFieldCep.setText(null);
        jTextFieldCidade.setText(null);
        jTextFieldComplemento.setText(null);
        jFormattedTextFieldCpf.setText(null);
        jTextFieldDataNascimento.setText(null);
        jTextFieldEstado.setText(null);
        jTextFieldLogradouro.setText(null);
        jTextFieldNome.setText(null);
        jTextFieldNomeMae.setText(null);
        jTextFieldNomePai.setText(null);
        jTextFieldNumero.setText("0");
        jTextFieldPeso.setText("0.0");
        jFormattedTextFieldRg.setText(null);
        
        telefonesListModel.clear();
        premiacaoListModel.clear();
        partidasListModel.clear();
        timesListModel.clear();
        
        jComboBoxSexo.setSelectedIndex(0);
        jComboBoxCategoria.setSelectedIndex(0);

        //esp. da classe JBasquete
        jComboBoxPosicao.setSelectedIndex(0);
        jTextFieldNumeroCamisa.setText("0");
        jTextFieldTotalJogos.setText(null);
        jTextFieldTotalVitorias.setText(null);
        jTextFieldJogosSelecao.setText(null);
        jTextFieldVitoriasSelecao.setText(null);
        jTextFieldPontuacaoMedia.setText(null);
        jTextFieldPorcentegemLancesLivresConvertidos.setText(null);
        jTextFieldMediaFaltasSofridas.setText(null);
        jTextFieldMediaFaltasCometidas.setText(null);
    }

    private void preencherCampos() {
        ArrayList<String> telefones;
        ArrayList<Premiacao> premiacoes;
        ArrayList<Time> times;
        ArrayList<Partida> partidas;

        jTextFieldAltura.setText(Double.toString(jogador.getAltura()));
        jTextFieldBairro.setText(jogador.getEndereco().getBairro());
        jFormattedTextFieldCep.setText(jogador.getEndereco().getCep());
        jTextFieldCidade.setText(jogador.getEndereco().getCidade());
        jTextFieldComplemento.setText(jogador.getEndereco().getComplemento());
        jFormattedTextFieldCpf.setText(jogador.getCpf());

        if (jogador.getDataNascimento() == null) {
            jTextFieldDataNascimento.setText(null);
        } else {
            jTextFieldDataNascimento.setText(dateFormat.format(jogador.getDataNascimento()));
        }

        jTextFieldEstado.setText(jogador.getEndereco().getEstado());
        jTextFieldLogradouro.setText(jogador.getEndereco().getLogradouro());
        jTextFieldNome.setText(jogador.getNome());
        jTextFieldNomeMae.setText(jogador.getNomeMae());
        jTextFieldNomePai.setText(jogador.getNomePai());
        jTextFieldNumero.setText(jogador.getEndereco().getNumero().toString());
        jTextFieldPais.setText(jogador.getEndereco().getPais());
        jTextFieldPeso.setText(Double.toString(jogador.getPeso()));
        jFormattedTextFieldRg.setText(jogador.getRg());
        jTextFieldTotalVitorias.setText(Integer.toString(jogador.getTotalVitorias()));

        telefonesListModel.clear();

        telefones = jogador.getTelefones();
        for (String telefone : telefones) {
            telefonesListModel.addElement(telefone);
        }

        premiacaoListModel.clear();
        premiacoes = jogador.getPremiacoes();

        for (Premiacao p : premiacoes) {
            premiacaoListModel.addElement(p);
        }
        
        partidasListModel.clear();
        partidas = jogador.getHistoricoPartidas();
        
        for (Partida dadosPartida : partidas) {
            partidasListModel.addElement(dadosPartida);
        }

        timesListModel.clear();
        times= jogador.getClubes();
        
        for (Time time : times) {
            timesListModel.addElement(time);
        }
        
        switch (jogador.getSexo()) {
            case SEXO_MASCULINO_VALOR:
                jComboBoxSexo.setSelectedIndex(SEXO_MASCULINO_INDICE);
                break;
            case SEXO_FEMININO_VALOR:
                jComboBoxSexo.setSelectedIndex(SEXO_FEMININO_INDICE);
                break;
        }

        switch (jogador.getCategoria()) {
            case CATEGORIA_AMADOR_VALOR:
                jComboBoxCategoria.setSelectedIndex(CATEGORIA_AMADOR_INDICE);
                break;
            case CATEGORIA_PROFISSIONAL_VALOR:
                jComboBoxCategoria.setSelectedIndex(CATEGORIA_PROFISSIONAL_INDICE);
                break;
        }

        jComboBoxPosicao.setSelectedIndex(mapPosicao.get(jogador.getPosicao()));

        jTextFieldNumeroCamisa.setText("" + jogador.getNumeroCamisa());
        jTextFieldTotalJogos.setText("" + jogador.getTotalJogos());
        jTextFieldTotalVitorias.setText("" + jogador.getTotalVitorias());
        jTextFieldJogosSelecao.setText("" + jogador.getJogosSelecao());
        jTextFieldVitoriasSelecao.setText("" + jogador.getVitoriasSelecao());
        jTextFieldPontuacaoMedia.setText("" + jogador.getPontuacaoMedia());
        jTextFieldPorcentegemLancesLivresConvertidos.setText("" + jogador.getPorcentagemLancesLivresConvertidos());
        jTextFieldMediaFaltasSofridas.setText("" + jogador.getMediaFaltasSofridas());
        jTextFieldMediaFaltasCometidas.setText("" + jogador.getMediaFaltasCometidas());
    }

    private boolean validarCampos() {
        if (jTextFieldNome.getText().trim().length() == 0) {
            this.exibirInformacao("O valor do campo 'Nome' não foi informado.");
            jTextFieldNome.requestFocus();
            return false;
        }

        if (jTextFieldDataNascimento.getText().length() != 0) {
            try {
                dateFormat.parse(jTextFieldDataNascimento.getText());
            } catch (ParseException ex) {
                this.exibirInformacao("O valor do campo 'Data de Nascimento' é inválido.");
                jTextFieldDataNascimento.requestFocus();
                return false;
            }
        }


        try {
            Double.parseDouble(jTextFieldAltura.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Altura' é inválido.");
            jTextFieldAltura.requestFocus();
            return false;
        }
        try {
            Double.parseDouble(jTextFieldPeso.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Peso' é inválido.");
            jTextFieldPeso.requestFocus();
            return false;
        }
        if (!jTextFieldNumero.getText().equals("")) {
            try {
                Integer.parseInt(jTextFieldNumero.getText());
            } catch (Exception ex) {
                this.exibirInformacao("O valor do campo 'Número' é inválido.");
                jTextFieldNumero.requestFocus();
                return false;
            }
        }

        try {
            Integer.parseInt(jTextFieldNumeroCamisa.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Numero Camisa' é inválido.");
            jTextFieldNumeroCamisa.requestFocus();
            return false;
        }


        return true;
    }

    private void habilitarDesabilitarCampos() {
        boolean registroSelecionado = (jogador != null);
        jTextFieldAltura.setEnabled(modoAlteracao);
        jTextFieldBairro.setEnabled(modoAlteracao);
        jFormattedTextFieldCep.setEnabled(modoAlteracao);
        jTextFieldCidade.setEnabled(modoAlteracao);
        jTextFieldComplemento.setEnabled(modoAlteracao);
        jFormattedTextFieldCpf.setEnabled(modoAlteracao);
        jTextFieldDataNascimento.setEnabled(modoAlteracao);
        jTextFieldEstado.setEnabled(modoAlteracao);
        jTextFieldLogradouro.setEnabled(modoAlteracao);
        jTextFieldNome.setEnabled(modoAlteracao);
        jTextFieldNomeMae.setEnabled(modoAlteracao);
        jTextFieldNomePai.setEnabled(modoAlteracao);
        jTextFieldNumero.setEnabled(modoAlteracao);
        jTextFieldPais.setEnabled(modoAlteracao);
        jTextFieldPeso.setEnabled(modoAlteracao);
        jFormattedTextFieldRg.setEnabled(modoAlteracao);
        jTextFieldNumeroCamisa.setEnabled(modoAlteracao);
        jButtonNovo.setEnabled(modoAlteracao == false);
        jButtonAlterar.setEnabled(modoAlteracao == false && registroSelecionado == true);
        jButtonExcluir.setEnabled(modoAlteracao == false && registroSelecionado == true);
        jButtonPesquisar.setEnabled(modoAlteracao == false);
        jButtonSalvar.setEnabled(modoAlteracao);
        jButtonCancelar.setEnabled(modoAlteracao);
        jButtonAdicionarTelefone.setEnabled(modoAlteracao);
        jButtonRemoverTelefone.setEnabled(modoAlteracao);
        jButtonAdicionarPremiacao.setEnabled(modoAlteracao);
        jButtonRemoverPremiacao.setEnabled(modoAlteracao);
        jButtonAdionarPartida.setEnabled(modoAlteracao);
        jButtonAdionarPartida.setEnabled(modoAlteracao);
        jButtonRemoverPartida.setEnabled(modoAlteracao);
        jButtonAdicionarTime.setEnabled(modoAlteracao);
        jButtonRemoverTime.setEnabled(modoAlteracao);
        jComboBoxSexo.setEnabled(modoAlteracao);
        jComboBoxCategoria.setEnabled(modoAlteracao);
        jComboBoxPosicao.setEnabled(modoAlteracao);
        jTableJogadores.setEnabled(modoAlteracao == false);
    }

    private void salvarRegistro() {

        Endereco endereco;
        Date dataNascimento;
        
        ArrayList<Time> times = new ArrayList<Time>();
        ArrayList<Premiacao> premiacoes = new ArrayList<Premiacao>();
        ArrayList<String> telefones = new ArrayList<String>();
        ArrayList<Partida> partidas = new ArrayList<Partida>();
        
        if (this.validarCampos() == false) {
            return;
        }

        if (jTextFieldDataNascimento.getText().length() == 0) {
            dataNascimento = null;
        } else {
            try {
                dataNascimento = dateFormat.parse(jTextFieldDataNascimento.getText());
            } catch (ParseException ex) {
                this.exibirInformacao("Falha ao gravar a data de nascimento: " + ex.toString());
                return;
            }
        }

        endereco = new Endereco();
        endereco.setBairro(jTextFieldBairro.getText());
        endereco.setCep(jFormattedTextFieldCep.getText());
        endereco.setCidade(jTextFieldCidade.getText());
        endereco.setComplemento(jTextFieldComplemento.getText());
        endereco.setEstado(jTextFieldEstado.getText());
        endereco.setLogradouro(jTextFieldLogradouro.getText());
        endereco.setNumero(jTextFieldNumero.getText());
        endereco.setPais(jTextFieldPais.getText());

        if (novoRegistro == true) {
            jogador = new JogadorBasquete(jTextFieldNome.getText());
        } else {
            jogador.setNome(jTextFieldNome.getText());
        }


        for (int i = 0; i < telefonesListModel.size(); i++) {
            telefones.add((String) telefonesListModel.getElementAt(i));
        }

        for (int i = 0; i < premiacaoListModel.size(); i++) {
            Premiacao premiacao = (Premiacao) premiacaoListModel.getElementAt(i);
            premiacoes.add(premiacao);
        }

        for (int i = 0; i < partidasListModel.size(); i++) {
            partidas.add((Partida) partidasListModel.getElementAt(i));
        }

        for (int i = 0; i < timesListModel.size(); i++) {
            times.add((Time) timesListModel.getElementAt(i));
        }

        jogador.setEndereco(endereco);
        jogador.setDataNascimento(dataNascimento);
        jogador.setAltura(Double.parseDouble(jTextFieldAltura.getText()));
        jogador.setNomeMae(jTextFieldNomeMae.getText());
        jogador.setNomePai(jTextFieldNomePai.getText());
        jogador.setPeso(Double.parseDouble(jTextFieldPeso.getText()));
        jogador.setCpf(jFormattedTextFieldCpf.getText());
        jogador.setRg(jFormattedTextFieldRg.getText());
        jogador.setNumeroCamisa(Short.parseShort(jTextFieldNumeroCamisa.getText()));
        jogador.setTimes(times);
        jogador.setPremiacoes(premiacoes);
        jogador.setHistoricoPartidas(partidas);
        jogador.setTelefones(telefones);


        switch (jComboBoxSexo.getSelectedIndex()) {
            case SEXO_MASCULINO_INDICE:
                jogador.setSexo(SEXO_MASCULINO_VALOR);
                break;
            case SEXO_FEMININO_INDICE:
                jogador.setSexo(SEXO_FEMININO_VALOR);
                break;
        }

        switch (jComboBoxCategoria.getSelectedIndex()) {
            case CATEGORIA_AMADOR_INDICE:
                jogador.setCategoria(CATEGORIA_AMADOR_VALOR);
                break;
            case CATEGORIA_PROFISSIONAL_INDICE:
                jogador.setCategoria(CATEGORIA_PROFISSIONAL_VALOR);
                break;
        }

        jogador.setPosicao(mapPosicaoInversa.get(jComboBoxPosicao.getSelectedIndex()));

        if (novoRegistro == true) {
            controleJogador.adicionar(jogador);
        }

        modoAlteracao = false;
        novoRegistro = false;

        this.carregarListaJogadoresBasquete();
        this.habilitarDesabilitarCampos();
    }

    private void carregarListaJogadoresBasquete() {
        ArrayList<JogadorBasquete> listaJogadores = (ArrayList<JogadorBasquete>) controleJogador.getListaJogadores();

        DefaultTableModel model = (DefaultTableModel) jTableJogadores.getModel();
        model.setRowCount(0);
        for (JogadorBasquete b : listaJogadores) {
            model.addRow(new String[]{b.getNome(), b.getCpf()});
        }

        jTableJogadores.setModel(model);
    }

    private void exibirInformacao(String info) {
        JOptionPane.showMessageDialog(this, info, "Atenção", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonNovo = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        espaco_horizontal = new javax.swing.Box.Filler(new java.awt.Dimension(220, 0), new java.awt.Dimension(220, 0), new java.awt.Dimension(220, 32767));
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableJogadores = new javax.swing.JTable();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldDataNascimento = new javax.swing.JFormattedTextField(ftmData);
        jLabel10 = new javax.swing.JLabel();
        jTextFieldAltura = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldPeso = new javax.swing.JTextField();
        jComboBoxSexo = new javax.swing.JComboBox();
        jTextFieldNomeMae = new javax.swing.JTextField();
        jTextFieldNomePai = new javax.swing.JTextField();
        jFormattedTextFieldRg = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField(ftmCPF);
        jButtonRemoverTelefone = new javax.swing.JButton();
        jButtonAdicionarTelefone = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTelefones = new javax.swing.JList();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldLogradouro = new javax.swing.JTextField();
        jTextFieldNumero = new javax.swing.JTextField();
        jTextFieldComplemento = new javax.swing.JTextField();
        jTextFieldBairro = new javax.swing.JTextField();
        jTextFieldCidade = new javax.swing.JTextField();
        jFormattedTextFieldCep = new javax.swing.JFormattedTextField(ftmCEP);
        jTextFieldEstado = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldPais = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldNumeroCamisa = new javax.swing.JTextField();
        jComboBoxCategoria = new javax.swing.JComboBox();
        jComboBoxPosicao = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListPremiacao = new javax.swing.JList();
        jButtonAdicionarPremiacao = new javax.swing.JButton();
        jButtonRemoverPremiacao = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListTimes = new javax.swing.JList();
        jLabel33 = new javax.swing.JLabel();
        jButtonAdicionarTime = new javax.swing.JButton();
        jButtonRemoverTime = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonAdionarPartida = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListPartidas = new javax.swing.JList();
        jButtonRemoverPartida = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldTotalJogos = new javax.swing.JTextField();
        jTextFieldTotalVitorias = new javax.swing.JTextField();
        jTextFieldJogosSelecao = new javax.swing.JTextField();
        jTextFieldVitoriasSelecao = new javax.swing.JTextField();
        jTextFieldPontuacaoMedia = new javax.swing.JTextField();
        jTextFieldPorcentegemLancesLivresConvertidos = new javax.swing.JTextField();
        jTextFieldMediaFaltasSofridas = new javax.swing.JTextField();
        jTextFieldMediaFaltasCometidas = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro Jogador de Basquete");

        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar...");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        espaco_horizontal.setBackground(new java.awt.Color(17, 179, 218));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espaco_horizontal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAlterar, jButtonCancelar, jButtonExcluir, jButtonNovo, jButtonPesquisar, jButtonSalvar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(espaco_horizontal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel1.setText("Listar jogadores:");

        jTableJogadores.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Nome", "CPF"
            }
        ));
        jTableJogadores.getTableHeader().setReorderingAllowed(false);
        jTableJogadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableJogadoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableJogadores);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
        );

        jLabel2.setText("Nome:");

        jLabel3.setText("Data de Nascimento:");

        jLabel4.setText("Sexo:");

        jLabel5.setText("Nome da Mãe:");

        jLabel6.setText("Nome do Pai:");

        jLabel7.setText("RG:");

        jLabel8.setText("CPF:");

        jLabel9.setText("Telefones:");

        jTextFieldDataNascimento.setColumns(8);
        jTextFieldDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataNascimentoActionPerformed(evt);
            }
        });

        jLabel10.setText("Altura:");

        jLabel11.setText("Peso:");

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));
        jComboBoxSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSexoActionPerformed(evt);
            }
        });

        jFormattedTextFieldRg.setColumns(7);
        jFormattedTextFieldRg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldRgActionPerformed(evt);
            }
        });

        jButtonRemoverTelefone.setText("-");
        jButtonRemoverTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverTelefoneActionPerformed(evt);
            }
        });

        jButtonAdicionarTelefone.setText("+");
        jButtonAdicionarTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarTelefoneActionPerformed(evt);
            }
        });

        jListTelefones.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListTelefones);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNome)
                    .addComponent(jTextFieldNomeMae)
                    .addComponent(jTextFieldNomePai)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldDataNascimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                                .addGap(32, 32, 32)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(jFormattedTextFieldRg, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonRemoverTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAdicionarTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonAdicionarTelefone)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonRemoverTelefone))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Informações Gerais", jPanel6);

        jLabel12.setText("Logradouro:");

        jLabel13.setText("Número:");

        jLabel14.setText("Complemento:");

        jLabel15.setText("Bairro:");

        jLabel16.setText("Cidade:");

        jLabel17.setText("Estado:");

        jLabel18.setText("CEP:");

        jLabel31.setText("País:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLogradouro)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 600, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldEstado, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldComplemento))))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldBairro, jTextFieldCidade, jTextFieldPais});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(168, 168, 168))
        );

        jTabbedPane4.addTab("Endereço", jPanel7);

        jPanel8.setEnabled(false);

        jLabel19.setText("Categoria:");

        jLabel20.setText("Posição:");

        jLabel22.setText("N° camisa:");

        jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Amador", "Profissional" }));
        jComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriaActionPerformed(evt);
            }
        });

        String tempName[] = new String[Posicao.values().length];
        int tempINT = 0;

        for (Posicao p : Posicao.values()){
            tempName[tempINT] = p.name();
            tempINT++;
        }
        jComboBoxPosicao.setModel(new javax.swing.DefaultComboBoxModel(tempName));
        jComboBoxPosicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPosicaoActionPerformed(evt);
            }
        });

        jLabel30.setText("Premiações:");

        jListPremiacao.setModel(new DefaultListModel());
        jScrollPane3.setViewportView(jListPremiacao);

        jButtonAdicionarPremiacao.setText("+");
        jButtonAdicionarPremiacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarPremiacaoActionPerformed(evt);
            }
        });

        jButtonRemoverPremiacao.setText("-");
        jButtonRemoverPremiacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverPremiacaoActionPerformed(evt);
            }
        });

        jListTimes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jListTimes);

        jLabel33.setText("Times:");

        jButtonAdicionarTime.setText("+");
        jButtonAdicionarTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarTimeActionPerformed(evt);
            }
        });

        jButtonRemoverTime.setText("-");
        jButtonRemoverTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverTimeActionPerformed(evt);
            }
        });

        jLabel34.setText("Times:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addGap(114, 114, 114)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxPosicao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCategoria, 0, 282, Short.MAX_VALUE)
                            .addComponent(jTextFieldNumeroCamisa))
                        .addGap(302, 302, 302))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonRemoverTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonAdicionarTime, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)))
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonRemoverPremiacao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonAdicionarPremiacao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(204, 204, 204)
                    .addComponent(jLabel33)
                    .addContainerGap(529, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jComboBoxPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jTextFieldNumeroCamisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButtonAdicionarPremiacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoverPremiacao))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButtonAdicionarTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoverTime))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel33)
                    .addContainerGap(410, Short.MAX_VALUE)))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane3, jScrollPane5});

        jTabbedPane4.addTab("Ficha Técnica - Geral", jPanel8);

        jButtonAdionarPartida.setText("+");
        jButtonAdionarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdionarPartidaActionPerformed(evt);
            }
        });

        jListPartidas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jListPartidas);

        jButtonRemoverPartida.setText("-");
        jButtonRemoverPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverPartidaActionPerformed(evt);
            }
        });

        jLabel32.setText("Partidas:");

        jTextFieldTotalJogos.setEnabled(false);
        jTextFieldTotalJogos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalJogosActionPerformed(evt);
            }
        });

        jTextFieldTotalVitorias.setEnabled(false);

        jTextFieldJogosSelecao.setEnabled(false);

        jTextFieldVitoriasSelecao.setEnabled(false);

        jTextFieldPontuacaoMedia.setEnabled(false);

        jTextFieldPorcentegemLancesLivresConvertidos.setEnabled(false);

        jTextFieldMediaFaltasSofridas.setEnabled(false);

        jTextFieldMediaFaltasCometidas.setEnabled(false);

        jLabel29.setText("Média Faltas Cometidas:");

        jLabel28.setText("Média Faltas Sofridas:");

        jLabel26.setText("% Lances Livres Convertidos:");

        jLabel27.setText("Pontuação Média:");

        jLabel25.setText("Vitórias na Seleção:");

        jLabel24.setText("Jogos pela Seleção:");

        jLabel23.setText("Total de Vitórias:");

        jLabel21.setText("Total de Jogos:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26)
                            .addComponent(jLabel29)
                            .addComponent(jLabel28))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPontuacaoMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldMediaFaltasCometidas, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPorcentegemLancesLivresConvertidos, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldMediaFaltasSofridas, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldVitoriasSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldJogosSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(82, 82, 82)
                        .addComponent(jTextFieldTotalVitorias, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(94, 94, 94)
                        .addComponent(jTextFieldTotalJogos, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAdionarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRemoverPartida)))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAdionarPartida, jButtonRemoverPartida});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldJogosSelecao, jTextFieldMediaFaltasCometidas, jTextFieldMediaFaltasSofridas, jTextFieldPontuacaoMedia, jTextFieldPorcentegemLancesLivresConvertidos, jTextFieldTotalJogos, jTextFieldTotalVitorias, jTextFieldVitoriasSelecao});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldTotalJogos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonAdionarPartida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoverPartida))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jTextFieldTotalVitorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jTextFieldJogosSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jTextFieldVitoriasSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27)
                            .addComponent(jTextFieldPontuacaoMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jTextFieldPorcentegemLancesLivresConvertidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jTextFieldMediaFaltasSofridas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldMediaFaltasCometidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Ficha Técnica - Dados Partidas", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        // TODO add your handling code here:
        jogador = null;
        modoAlteracao = true;
        novoRegistro = true;
        this.limparCampos();
        this.habilitarDesabilitarCampos();
        this.jTextFieldNome.requestFocus();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        // TODO add your handling code here:
        modoAlteracao = true;
        novoRegistro = false;
        this.habilitarDesabilitarCampos();
        this.jTextFieldNome.requestFocus();
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:
        this.salvarRegistro();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        if (novoRegistro == true) {
            this.limparCampos();
        } else {
            this.preencherCampos();
        }
        modoAlteracao = false;
        novoRegistro = false;
        this.habilitarDesabilitarCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextFieldDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDataNascimentoActionPerformed

    private void jComboBoxSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSexoActionPerformed

    private void jButtonRemoverTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverTelefoneActionPerformed
        // TODO add your handling code here:
        if (jListTelefones.getSelectedIndex() != -1) {
            telefonesListModel.removeElementAt(jListTelefones.getSelectedIndex());
        }
    }//GEN-LAST:event_jButtonRemoverTelefoneActionPerformed

    private void jButtonAdicionarTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarTelefoneActionPerformed
        // TODO add your handling code here:
        CadastroTelefone cadastroTelefone = new CadastroTelefone(this, true);
        cadastroTelefone.setVisible(true);

        if (cadastroTelefone.getTelefone() != null) {
            telefonesListModel.addElement(cadastroTelefone.getTelefone());
        }

        cadastroTelefone.dispose();

    }//GEN-LAST:event_jButtonAdicionarTelefoneActionPerformed

    private void jTextFieldTotalJogosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalJogosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalJogosActionPerformed

    private void jComboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaActionPerformed
    }//GEN-LAST:event_jComboBoxCategoriaActionPerformed

    private void jButtonAdicionarPremiacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarPremiacaoActionPerformed
        // TODO add your handling code here:
        CadastroPremiacao cadastro = new CadastroPremiacao(this, true);
        cadastro.setVisible(true);
        if (cadastro.getPremiacao() != null) {
            premiacaoListModel.addElement(cadastro.getPremiacao());
        }
        cadastro.dispose();
    }//GEN-LAST:event_jButtonAdicionarPremiacaoActionPerformed

    private void jButtonAdionarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdionarPartidaActionPerformed
        // TODO add your handling code here:
        CadastroPartida cadastro = new CadastroPartida(this, true);
        cadastro.setVisible(true);
        if (cadastro.getPartida() != null) {
            partidasListModel.addElement(cadastro.getPartida());
        }
        cadastro.dispose();
    }//GEN-LAST:event_jButtonAdionarPartidaActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:
        this.controleJogador.remover(jogador);
        jogador = null;
        this.limparCampos();
        this.carregarListaJogadoresBasquete();
        this.habilitarDesabilitarCampos();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void pesquisarJogador(String pesquisa) {
        JogadorBasquete jogadorAchado = controleJogador.pesquisar(pesquisa);

        if (jogadorAchado == null) {
            exibirInformacao("Jogador não encontrado.");
        } else {
            this.jogador = jogadorAchado;
            this.limparCampos();
            this.preencherCampos();
            this.habilitarDesabilitarCampos();
        }
    }

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        // TODO add your handling code here:
        String pesquisa = JOptionPane.showInputDialog("Informe o nome do Jogador.");
        if (pesquisa != null) {
            this.pesquisarJogador(pesquisa);
        }
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonRemoverPremiacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverPremiacaoActionPerformed
        // TODO add your handling code here:
        if (jListPremiacao.getSelectedIndex() != -1) {
            premiacaoListModel.removeElementAt(jListPremiacao.getSelectedIndex());
        }
    }//GEN-LAST:event_jButtonRemoverPremiacaoActionPerformed

    private void jTableJogadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableJogadoresMouseClicked
        // TODO add your handling code here:
        if (jTableJogadores.isEnabled()) {
            DefaultTableModel model = (DefaultTableModel) jTableJogadores.getModel();
            String nome = (String) model.getValueAt(jTableJogadores.getSelectedRow(), 0);
            this.pesquisarJogador(nome);
        }
    }//GEN-LAST:event_jTableJogadoresMouseClicked

    private void jButtonRemoverPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverPartidaActionPerformed
        // TODO add your handling code here:
        if (jListPartidas.getSelectedIndex() != -1) {
            if(modoAlteracao)
            partidasListModel.removeElementAt(jListPartidas.getSelectedIndex());
            
            
        }
    }//GEN-LAST:event_jButtonRemoverPartidaActionPerformed

    private void jButtonRemoverTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverTimeActionPerformed
        // TODO add your handling code here:
        if (jListTimes.getSelectedIndex() != -1) {
            timesListModel.removeElementAt(jListTimes.getSelectedIndex());
        }
    }//GEN-LAST:event_jButtonRemoverTimeActionPerformed

    private void jButtonAdicionarTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarTimeActionPerformed
        // TODO add your handling code here:
        CadastroTime cadastro = new CadastroTime(this, true);
        cadastro.setVisible(true);
        if (cadastro.getTime() != null) {
            timesListModel.addElement(cadastro.getTime());
        }
        cadastro.dispose();
    }//GEN-LAST:event_jButtonAdicionarTimeActionPerformed

    private void jFormattedTextFieldRgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldRgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldRgActionPerformed

    private void jComboBoxPosicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPosicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPosicaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler espaco_horizontal;
    private javax.swing.JButton jButtonAdicionarPremiacao;
    private javax.swing.JButton jButtonAdicionarTelefone;
    private javax.swing.JButton jButtonAdicionarTime;
    private javax.swing.JButton jButtonAdionarPartida;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonRemoverPartida;
    private javax.swing.JButton jButtonRemoverPremiacao;
    private javax.swing.JButton jButtonRemoverTelefone;
    private javax.swing.JButton jButtonRemoverTime;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxCategoria;
    private javax.swing.JComboBox jComboBoxPosicao;
    private javax.swing.JComboBox jComboBoxSexo;
    private javax.swing.JFormattedTextField jFormattedTextFieldCep;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldRg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListPartidas;
    private javax.swing.JList jListPremiacao;
    private javax.swing.JList jListTelefones;
    private javax.swing.JList jListTimes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTableJogadores;
    private javax.swing.JTextField jTextFieldAltura;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JFormattedTextField jTextFieldDataNascimento;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldJogosSelecao;
    private javax.swing.JTextField jTextFieldLogradouro;
    private javax.swing.JTextField jTextFieldMediaFaltasCometidas;
    private javax.swing.JTextField jTextFieldMediaFaltasSofridas;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNomeMae;
    private javax.swing.JTextField jTextFieldNomePai;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldNumeroCamisa;
    private javax.swing.JTextField jTextFieldPais;
    private javax.swing.JTextField jTextFieldPeso;
    private javax.swing.JTextField jTextFieldPontuacaoMedia;
    private javax.swing.JTextField jTextFieldPorcentegemLancesLivresConvertidos;
    private javax.swing.JTextField jTextFieldTotalJogos;
    private javax.swing.JTextField jTextFieldTotalVitorias;
    private javax.swing.JTextField jTextFieldVitoriasSelecao;
    // End of variables declaration//GEN-END:variables
}
