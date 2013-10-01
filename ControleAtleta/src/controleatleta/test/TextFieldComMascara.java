package controleatleta.test;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class TextFieldComMascara extends JFrame {

    private JFormattedTextField telefone;//campo para telefone  
    private JFormattedTextField data;//campo para data  
    private MaskFormatter ftmTelefone;//Atributo formatador para telefone  
    private MaskFormatter ftmData;//Atributo formatador para data 

    public TextFieldComMascara() throws ParseException {

        ftmTelefone = new MaskFormatter("####-####");
        ftmData = new MaskFormatter("##/##/####");

        telefone = new JFormattedTextField(ftmTelefone);
        data = new JFormattedTextField(ftmData);

        ftmTelefone.setValidCharacters("0123456789");
        ftmData.setValidCharacters("0123456789");

        telefone.setColumns(6);
        data.setColumns(6);

        add(telefone);
        add(data);

        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            TextFieldComMascara teste = new TextFieldComMascara();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
