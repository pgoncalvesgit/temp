
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;


// classe que representa no da arvore
// por simplicidade esta "hard-coded" para o problema e com atributos de acesso publico

class Node {
    public String matricula;
    public int divida;
    public int altura;
    public boolean temDivida;
    public boolean vermelho;
    public Node pai;
    public Node left;
    public Node right;

    // Construtores
    Node(String matricula, int divida, Node pai) {
        this.matricula = matricula;
        this.divida = divida;
        this.pai = pai;
        vermelho = true;
        altura = 1;
        temDivida = true;
        left = null;
        right = null;
    }
    Node(String matricula, int divida, Node pai, boolean vermelho) {
        this.matricula = matricula;
        this.divida = divida;
        this.pai = pai;
        this.vermelho = vermelho;
        altura = 1;
        temDivida = true;
        left = null;
        right = null;
    }

    Node(String matricula, int divida, Node l, Node r, Node pai) {
        this.matricula = matricula;
        this.divida = divida;
        this.pai = pai;
        vermelho = true;
        altura = 1;
        temDivida = true;
        left = l;
        right = r;
    }

    // Comparadores. Unico criterio e a String matricula. Restantes campos sao ignorados
    int compareTo(String matricula) {
        return this.matricula.compareTo(matricula);
    }

    int compareTo(Node otherNode) {
        return this.compareTo(otherNode.matricula);
    }
}



// classe que representa arvore binaria. Implementacao hard-coded para o problema.

class BST {

    /* raiz da arvore. Null quando arvore vazia */
    protected Node root = null;

    /* construtores */

    public BST() {
        root = null;
    }

    public BST (Node no) {
        root = no;
    }

    public BST(String matricula, int divida) {
        root = new Node(matricula, divida, null);
    }

    // encontra determinado veiculo na arvore (null se nao encontrado)
    public Node get(Node no) {
        return this.get(no.matricula);
    }

    public Node get(String matricula) {
        Node no = root;
        while (no != null) {
            if (no.compareTo(matricula) == 0) {
                return no;
            }
            no = ((no.compareTo(matricula) > 0) ? no.left : no.right);
        }
        return null;
    }

    // adiciona novo no a arvore. Se ja existe atualiza divida acumulado
    /*public void add(String matricula, int divida) {
        root = addOutro(matricula, divida, root);
        return;
    }*/

    
    protected void trocaUmEsquerda(Node node){
        Node temp;
        if (node.pai != null){
            if (node.pai.left == node){
                node.pai.left = node.left;
            }
            else{
                node.pai.right = node.left;
            }
        }
        else{
            this.root = node.left;
            this.root.vermelho = false;
        }

        node.left.pai = node.pai;
        node.pai = node.left;
        temp = node.left.right;
        node.left.right = node;
        node.left = temp;

        if (node.left != null){
            node.left.pai = node;
        }
        
    }
    protected void trocaUmDireita(Node node){
        Node temp;
        if (node.pai != null){
            if (node.pai.right == node){
                node.pai.right = node.right;
            }
            else{
                node.pai.left = node.right;
            }
        }
        else{
            this.root = node.right;
            this.root.vermelho = false;
        }

        node.right.pai = node.pai;
        node.pai = node.right;
        temp = node.right.left;
        node.right.left = node;
        node.right = temp;

        if (node.right != null){
            node.right.pai = node;
        }
        
    }
    
    protected void addOutro(String matricula, int valor, Node node){

        Node pai = null;
        Node temp;
        boolean esquerda = false;
        boolean segundaEsquerda = false;
        
        if (this.root == null){
            this.root = new Node(matricula, valor, pai, false);
            return;
        }

        while(node != null){
            if (node.compareTo(matricula) == 0){
                if (!node.temDivida){
                    node.temDivida = true;
                }
                node.divida += valor;
                return;
            }
            else if (node.compareTo(matricula) >  0){
                if (node.right != null && node.right.vermelho && node.left != null && node.left.vermelho){
                    node.left.vermelho = false;
                    node.right.vermelho = false;
                    if (node.pai != null){
                        node.vermelho = true;
                        if (node.pai.vermelho){
                            if (node.pai.pai.left == node.pai){
                                if (node.pai.left == node){
                                    this.trocaUmEsquerda(node.pai.pai);
                                }
                                else{
                                    //1 passo
                                    this.trocaUmDireita(node.pai);
                                    //2 passo
                                    this.trocaUmEsquerda(node.pai);
                                }
                            }
                            else{
                                if (node.pai.left == node){
                                    //1 passo
                                    this.trocaUmEsquerda(node.pai);
                                    //2 passo
                                    this.trocaUmDireita(node.pai);
                                }
                                else{
                                    this.trocaUmDireita(node.pai.pai);
                                }
                            }
                        }
                        
                    }
                }
                
                pai = node;
                int alt;
                if (node.left == null){
                    alt = 0;
                }
                else{
                    alt = node.left.altura;
                }
                if (alt + 1 == node.altura){
                    node.altura++;
                }
                node = node.left;
                segundaEsquerda = true;
                esquerda = segundaEsquerda;
            }
            else{
                if (node.right != null && node.right.vermelho && node.left != null && node.left.vermelho){
                    node.left.vermelho = false;
                    node.right.vermelho = false;
                    if (node.pai != null){
                        node.vermelho = true;
                        if (node.pai.vermelho){
                            if (node.pai.pai.left == node.pai){
                                if (node.pai.left == node){
                                    this.trocaUmEsquerda(node.pai.pai);
                                }
                                else{
                                    //1 passo
                                    this.trocaUmDireita(node.pai);
                                    //2 passo
                                    this.trocaUmEsquerda(node.pai);
                                }
                            }
                            else{
                                if (node.pai.left == node){
                                    //1 passo
                                    this.trocaUmEsquerda(node.pai);
                                    //2 passo
                                    this.trocaUmDireita(node.pai);
                                }
                                else{
                                    this.trocaUmDireita(node.pai.pai);
                                }
                            }
                        }
                        
                    }
                }
                
                pai = node;
                int alt;
                if (node.right == null){
                    alt = 0;
                }
                else{
                    alt = node.right.altura;
                }
                if (alt + 1 == node.altura){
                    node.altura++;
                }
                node = node.right;
                segundaEsquerda = false;
                esquerda = segundaEsquerda;
            }
        }
        
        node = new Node(matricula, valor, pai);
        if (esquerda){
            pai.left = node;
        }
        else{
            pai.right = node;
        }
    }

    // recursivo... pode dar mau resultado para conjuntos grandes e degenerados    
    protected Node add(String matricula, int valor, Node node, Node pai) {
        // veiculo ainda nao existe, cria novo no para este veiculo.
        if (node == null) {
            return new Node (matricula, valor, pai);
        }

        // veiculo existe, adiciona a valor acumulado
        if (node.compareTo(matricula) == 0) {
            if (!node.temDivida){
                node.temDivida = true;
            }
            node.divida += valor;
        // ainda nao encontrou. desce mais um nivel
        } else {
            if (node.compareTo(matricula) > 0) {
                node.left = add(matricula, valor, node.left, node);
                node.altura++;
            } else {                               
                node.right = add(matricula, valor, node.right, node);
                node.altura++;
            }
        }
        return node;
    }

    public void removeDiferente(String matricula){
        Node no = get(matricula);
        no.temDivida = false;
    }

    // remove veiculo da arvore
    public void remove(Node no) {
        remove(no.matricula);
    }

    public void remove(String matricula){
        root = remove(matricula, root);
    }

    protected Node remove(String matricula, Node no) {
        if (no == null) {   // arvore vazia ou no nao encontrado
            return null;
        }
        if (no.compareTo(matricula) == 0) {  // remove este no
            if (no.left == null) {              // um unico filho, "linka" e sai
                return no.right;
            } else if (no.right == null) {      // idem...
                return no.left;
            } else {                            // dois filhos...
                // troca valores com rigthmost e depois remove...
                Node aux = getRightmost(no.left);
                no.matricula = aux.matricula;
                no.divida = aux.divida;
                aux.matricula = matricula; // valor nao precisa de ser mudado...
                no.left = remove(matricula, no.left);
            }
        } else {                                // continua a descer pela esq ou dir
            if (no.compareTo(matricula) > 0) {
                // pela esquerda...
                no.left = remove(matricula, no.left);
            } else {        // ou pela direita...
                no.right = remove(matricula, no.right);
            }
        }
        return no;
    }

    protected Node getRightmost(Node no) {
        return ((no.right == null) ? no : getRightmost(no.right));
    }

    // imprime em ordem. para debugging e verificacao
    void printInOrder(){
        printInOrder(root);
    }

    void printInOrder(Node no){
        if (no==null)
            return;
        printInOrder(no.left);
        if (no.temDivida){
            System.out.println(no.matricula + " VALOR EM DIVIDA " + no.divida);
        }
        printInOrder(no.right);
    }
}

public class TP2_TarefaC{

    public static void main(String[] arguments) throws FileNotFoundException {

        String input, comando;
        int valor;
        String matricula;
        StringTokenizer st;
        
        BST tree = new BST();
        //System.setIn(new FileInputStream(new File("C:\\Users\\Alex\\Desktop\\input.txt")));
        do {  // enquanto houver mais linhas para ler...
            input = readLn(200);
            st = new StringTokenizer(input.trim());
                
            comando = st.nextToken();
            if (comando.equals("PORTICO")){
                matricula = new String(st.nextToken());
                valor = Integer.parseInt(st.nextToken());
                tree.addOutro(matricula, valor, tree.root);
                Node no = tree.get(matricula);
                if (no.divida == 0) {tree.removeDiferente(matricula); }
            }
            else if (comando.equals("PAGAMENTO")){
                matricula = new String(st.nextToken());
                valor = Integer.parseInt(st.nextToken());
                tree.addOutro(matricula, (valor * -1), tree.root);
                Node no = tree.get(matricula);
                if (no.divida == 0) {tree.removeDiferente(matricula); }
            }
            else if (comando.equals("SALDO")){
                matricula = new String(st.nextToken());
                Node no = tree.get(matricula);
                if (no==null){
                    System.out.println(matricula + " SEM REGISTO");
                }
                else if (no.temDivida){
                    System.out.println(matricula + " VALOR EM DIVIDA " + no.divida);
                }
                else{
                    System.out.println(matricula + " SEM REGISTO");
                }
            }
            else if (comando.equals("LISTA")){
                tree.printInOrder();
            }
            else if (comando.equals("TERMINA")){
                return;
            }
        } while (true);
    }

    // leitura do input
    static String readLn (int maxLg){ //utility function to read from stdin
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";
        try {
            while (lg < maxLg){
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e){
            return (null);
        }
        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }
    
}
