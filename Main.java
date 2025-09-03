import java.util.ArrayList;
import java.util.Scanner;

class Vectors{
    private int dim;
    ArrayList<Double>components;
    Vectors(int n, Scanner sc){
        this.dim=n;
        components = new ArrayList<>();
        for(int i=0;i<n;i++){
            Double temp;
            temp=sc.nextDouble();
            this.components.add(temp);
        }
    }

    Vectors(Vectors v){
        this.dim=v.dim;
        components = new ArrayList<>();
        for(int i=0;i<dim;i++) this.components.add(v.components.get(i));
    }

    public Vectors makeCopy(Vectors v){
        Vectors copy = new Vectors(v);
        return copy;
    }

    public void dispVect(){
        System.out.println();
        for(Double c : this.components){
            System.out.println(c);
        }
    }

    public Double dot(Vectors v){
        Double ret=0D;
        for(int i=0;i<this.dim;i++){
            ret+=this.components.get(i)*v.components.get(i);
        }
        return ret;
    }

    public void scale(Double c){
        for(int i=0;i<this.dim;i++){
            this.components.set(i, c * this.components.get(i));
        }
    }

    public Vectors project(Vectors v1, Vectors v2){
        Vectors ret = new Vectors(v2);
        return ret;
    }
}

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int dim, n;
        System.out.print("Enter the dimension of vectors: ");
        dim = sc.nextInt();
        System.out.print("Enter the number of vectors: ");
        n = sc.nextInt();
        ArrayList<Vectors> inputVectors= new ArrayList<Vectors>();
        for(int i=0;i<n;i++){
            Vectors v = new Vectors(dim, sc);
            inputVectors.add(v);
        }
        sc.close();
        System.out.println(inputVectors.get(0).dot(inputVectors.get(1)));
    }
}