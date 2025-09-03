import java.util.ArrayList;
import java.util.Scanner;

class Vectors{

    private int dim;
    private ArrayList<Double>components;

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

    Vectors(int n){
        this.dim=n;
        components = new ArrayList<>();
        for(int i=0;i<this.dim;i++){
            this.components.add(0D);
        }
    }

    public Double getComponent(int n){
        return this.components.get(n);
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
        Double scalingFactor=(v1.dot(v2))/(v2.dot(v2));
        ret.scale(scalingFactor);
        return ret;
    }

    public Vectors completeProjection(ArrayList<Vectors>v){
        Vectors ret = new Vectors(this);
        Vectors proj = new Vectors(this.dim);
        for(int i=0;i<v.size();i++){
            proj.add(this.project(this, v.get(i)));
        }
        ret=ret.subtract(proj);
        return ret;
    }

    public void add(Vectors v){
        for(int i=0;i<this.dim;i++){
            this.components.set(i, this.components.get(i)+v.components.get(i));
        }
    }

    public Vectors subtract(Vectors v){
        Vectors ret = new Vectors(this.dim);
        for(int i=0;i<this.dim;i++){
            ret.components.set(i,this.components.get(i)-v.components.get(i));
        }
        return ret;
    }

    public void normalize(){
        Double mag = Math.sqrt(this.dot(this));
        for(int i=0;i<this.components.size();i++)
        this.components.set(i, this.components.get(i)/mag);
    }

}

class HOME{

    private int dim;
    private int n;
    private ArrayList<Vectors> input;

    HOME(Scanner sc){
        input = new ArrayList<Vectors>();
        System.out.print("Enter the dimension of vectors: ");
        dim = sc.nextInt();
        System.out.print("Enter the number of vectors: ");
        n = sc.nextInt();
        for(int i=0;i<n;i++){
            Vectors v = new Vectors(dim, sc);
            input.add(v);
        }
    }

    public ArrayList<Vectors> getInput(){
        return this.input;
    }

    public void displayVectorsList(){
        for(int i=0;i<dim;i++){
            for(int j=0;j<n;j++){
                System.out.print(this.input.get(j).getComponent(i)+ "    ");
            }
            System.out.print("\n");
        }
    }

    public void displayOrthogonalList(ArrayList<Vectors>v){
        System.out.println("The orthogonal set of vectors generated from the given vectors are: ");
        for(int i=0;i<dim;i++){
            for(int j=0;j<n;j++){
                System.out.print(v.get(j).getComponent(i)+ "    ");
            }
            System.out.print("\n");
        }
    }

    public void displayOrthonormalList(ArrayList<Vectors>v){
        System.out.println("The orthonormal set of vectors generated from the given vectors are: ");
        for(int i=0;i<dim;i++){
            for(int j=0;j<n;j++){
                v.get(j).normalize();
                System.out.print(v.get(j).getComponent(i)+ "    ");
            }
            System.out.print("\n");
        }
    }

    public ArrayList<Vectors> gramSchmidthOrthogonalization(){
        ArrayList<Vectors>ret= new ArrayList<>();
        ret.add(input.get(0));
        for(int i=1;i<input.size();i++){
            ret.add(input.get(i).completeProjection(ret));
        }
        return ret;
    }

}

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        HOME home = new HOME(sc);
        sc.close();
        ArrayList<Vectors>orthogonal = home.gramSchmidthOrthogonalization();
        home.displayOrthogonalList(orthogonal);
        home.displayOrthonormalList(orthogonal);
    }
}