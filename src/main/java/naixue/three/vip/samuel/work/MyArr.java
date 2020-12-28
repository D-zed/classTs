package naixue.three.vip.samuel.work;

import java.util.Arrays;

public class MyArr<T> {

    public Object [] arr;
    public Object [][] arr2;

   public int rowLength;
    public  int colLength;

    public MyArr(int size){
        rowLength=size;
        createArr(size);
    }

    public MyArr(int size,int size2){
        rowLength=size;
        colLength=size2;
        createArr2(size,size2);
    }

    private void createArr2(int size, int size2) {
        if (arr2==null){
            arr2=new Object[size][size2];
        }
    }


    public void createArr(int size ){
        if (arr==null){
            arr=new Object[size];
        }
    }

    public T getValByIndex(int index){
       return (T) arr[index];
    }



    public T getValByIndex2(int index,int index2){
        return (T) arr2[index][index2];
    }

    public void setValByIndex(int index,T val){
        arr[index]=val;
    }

    public void setValByIndex2(int index,int index2,T val){
        arr2[index][index2]=val;
    }

    @Override
    public String toString() {
        if (arr!=null){
            return Arrays.toString(arr);
        }else {
            StringBuilder a= new StringBuilder();
            for (int i = 0; i < arr2.length; i++) {
                a.append(Arrays.toString(arr2[i]));
            }
            return a.toString();
        }

    }

    public static void main(String[] args) {
        MyArr<Integer> integerMyArr = new MyArr<>(5);
        integerMyArr.setValByIndex(1,5);
        System.out.println(integerMyArr);


        MyArr<Integer>  integerMyArr1=new MyArr<>(2,5);
        integerMyArr1.setValByIndex2(1,3,6);
        System.out.println(integerMyArr1);
        Integer valByIndex2 = integerMyArr1.getValByIndex2(1, 3);
        System.out.println(valByIndex2);


        for (int i = 0; i < integerMyArr1.rowLength; i++) {
            System.out.println();
            for (int j = 0; j < integerMyArr1.colLength; j++) {
                System.out.print(integerMyArr1.getValByIndex2(i,j));
                System.out.print(" ");
            }
        }

    }

}
