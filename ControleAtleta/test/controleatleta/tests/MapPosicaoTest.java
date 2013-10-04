package controleatleta.tests;

import controleatleta.Posicao;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author nanson
 */
public class MapPosicaoTest {

    private Map<Posicao, Integer> posicao;
    private Map<Integer, Posicao> posicao_inversa;

    public MapPosicaoTest() {
    }

    @BeforeClass
    public void setUpClass() {
        this.posicao = new EnumMap<Posicao, Integer>(Posicao.class);
        this.posicao_inversa = new HashMap<Integer, Posicao>();

        int temp = 0;

        for (Posicao valor : Posicao.values()) {
            posicao.put(valor, temp);
            posicao_inversa.put(temp, valor); // sem paciencia para procurar a chave 
            temp++;
        }
    }

    @AfterClass
    public void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}