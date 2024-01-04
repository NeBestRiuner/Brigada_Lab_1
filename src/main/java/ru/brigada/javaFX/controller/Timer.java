package ru.brigada.javaFX.controller;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Border;
import ru.brigada.javaFX.model.Element;
import ru.brigada.javaFX.model.ElementArrayList;

import static java.lang.Math.sqrt;

public class Timer extends AnimationTimer {
    int iArray[];
    int workeds[];
    int obmens[];
    int wrongTime;
    int lengthArray;
    int unitedElements;
    int minNumPart=-1;
    private long lastTimes[];
    private long lastTime3;
    private boolean running;
    private int n;
    private Element twoDiamArray[][];
    @Override
    public void start() {
        super.start();
        running = true;
    }
    @Override
    public void stop() {
        super.stop();
        running = false;
    }
    @Override
    public void handle(long now) {
        int  i;
        if(wrongTime==0||wrongTime==-1){
            for(i=0; i<n;i++){
                long animI = (now-lastTimes[i])/1_000_000_000;
                sort(now,animI,i);
            }
            wrongTime=0;
            for(i = 0;i<n;i++){
                if(workeds[i]!=-1){
                    wrongTime=-1;
                }
            }
            if(wrongTime==0){// если все строки отсортированы
                wrongTime=1;
                lengthArray=ElementArrayList.getInstance().size();// сохраняем размер исходного списка
                ElementArrayList.getInstance().clear(); // очищаем список для последующей записи
                lastTime3=now;
                unitedElements=0;// кол-во элементов в будущем выходном списке
            }
        }else{
            if(unitedElements<lengthArray){// пока все элементы не будут перенесены в выходной список
                long anim = (now-lastTime3)/1_000_000_000;
                unite(n,anim,now); // слияние
            }else{
                System.out.println("Xnj");
                this.stop();
            }
        }
    }
    public Timer(long lastTime){
        int max = ElementArrayList.getInstance().get(0).getNumber();
        this.n = (int)sqrt(ElementArrayList.getInstance().size())+1;
        int i,j;
        this.twoDiamArray = new Element[n][n];
        for(i = 0; i < ElementArrayList.getInstance().size(); i++){
            twoDiamArray[i/n][i%n] = ElementArrayList.getInstance().get(i);
            if (ElementArrayList.getInstance().get(i).getNumber() > max) //распределение
                max = ElementArrayList.getInstance().get(i).getNumber();
        }
        for (j = n * n - ElementArrayList.getInstance().size()+1; j < n; j++){ // Заполнение "хвоста" последнего
            twoDiamArray[n - 1][j] = new Element(max+1);
        }
        ElementArrayList.setMaxValue(max);
        MainController.reshape_sort(n,twoDiamArray);
        lastTimes = new long[n];
        for(i = 0;i<n; i++){
            lastTimes[i]=lastTime;
        }
        iArray = new int[n];
        for(i = 0;i<n; i++){
            iArray[i] = 0;
        }
        workeds = new int[n];
        for (i = 0; i<n; i++){
            workeds[i]=0;
        }
        obmens = new int[n];
        for (i = 0; i<n; i++){
            obmens[i]=0;
        }
        this.wrongTime = 0;
        this.lastTime3=lastTime;
    }
    private void obmen(int i){
        if(twoDiamArray[i][iArray[i]].getLayoutY()>=((int)i)*100&&twoDiamArray[i][iArray[i]].getLayoutX()<=(iArray[i]+1)*40+
                ElementArrayList.getInstance().size()*40){
            twoDiamArray[i][iArray[i]].setLayoutY(twoDiamArray[i][iArray[i]].getLayoutY()-1);
        }else{
            if(twoDiamArray[i][iArray[i]].getLayoutX()<=(iArray[i]+1)*40+ElementArrayList.getInstance().size()*40){
                twoDiamArray[i][iArray[i]].setLayoutX(twoDiamArray[i][iArray[i]].getLayoutX()+1);
                twoDiamArray[i][iArray[i]+1].setLayoutX(twoDiamArray[i][iArray[i]+1].getLayoutX()-1);
            }else{
                if(twoDiamArray[i][iArray[i]].getLayoutY()<=((int)i)*100+50){
                    twoDiamArray[i][iArray[i]].setLayoutY(twoDiamArray[i][iArray[i]].getLayoutY()+1);
                }else{
                    obmens[i]=1;
                }
            }
        }
    }
    private void sort(long now, long anim, int i ){
        if(anim >= 2 && workeds[i]!= -1) {
            if(twoDiamArray[i][iArray[i]]!=null)
            twoDiamArray[i][iArray[i]].setStyle("-fx-background-color: #2850f0");
            if( anim >=4) {
                if(iArray[i]+1<n){
                    if(twoDiamArray[i][iArray[i]+1]!=null) {
                        twoDiamArray[i][iArray[i] + 1].setStyle("-fx-background-color: #f0c828");
                    }else {
                        lastTimes[i] = now;
                        if(twoDiamArray[i][iArray[i]]!=null){
                            twoDiamArray[i][iArray[i]].setStyle("-fx-background-color: #fff");
                        }iArray[i] = 0;
                        if (workeds[i] == 1) {
                            workeds[i] = 0;
                        } else {
                            workeds[i] = -1;
                        }
                    }
                }else{
                        lastTimes[i] = now;
                        twoDiamArray[i][iArray[i]].setStyle("-fx-background-color: #fff");
                        iArray[i] = 0;
                        if (workeds[i] == 1) {
                            workeds[i] = 0;
                        } else {
                            workeds[i] = -1;
                        }
                }
                if(anim>=6){
                    if (iArray[i]+1<n) {
                        if(twoDiamArray[i][iArray[i]+1]!=null)
                        if ((twoDiamArray[i][iArray[i]].getNumber()>           // проверка условия для обмена
                                twoDiamArray[i][iArray[i]+1].getNumber())) {
                            // показываем цветом, что условие выполнено
                            twoDiamArray[i][iArray[i]].setStyle("-fx-background-color: #008000");
                            twoDiamArray[i][iArray[i]+1].setStyle("-fx-background-color: #008000");
                            workeds[i]=1;
                            if(anim>=8){
                                // анимация обмена
                            if(obmens[i]!=1){
                                obmen(i); // метод для обмена двух элементов
                            }else{
                                    // перестановка три стакана
                                    Element elem = twoDiamArray[i][iArray[i]];
                                    twoDiamArray[i][iArray[i]] = twoDiamArray[i][iArray[i] + 1];
                                    twoDiamArray[i][iArray[i] + 1] = elem;
                                    // перерисовываем два элемента
                                    twoDiamArray[i][iArray[i]].setLayoutY(((int)i)*100+50);
                                    twoDiamArray[i][iArray[i]].setLayoutX((40*(iArray[i]))+ElementArrayList.getInstance().size()*40);
                                    twoDiamArray[i][iArray[i]+1].setLayoutY(((int)i)*100+50);
                                    twoDiamArray[i][iArray[i]+1].setLayoutX((40*(iArray[i]+1))+ElementArrayList.getInstance().size()*40);
                                    lastTimes[i] = now;
                                    twoDiamArray[i][iArray[i]].setStyle("-fx-background-color: #fff");
                                    twoDiamArray[i][iArray[i] + 1].setStyle("-fx-background-color: #fff"); // поменяли на белые
                                    iArray[i] += 1;
                                    obmens[i]=0;
                                }
                            }
                        }else{
                            twoDiamArray[i][iArray[i]].setStyle("-fx-background-color: #FF0000");
                            twoDiamArray[i][iArray[i]+1].setStyle("-fx-background-color: #FF0000");
                            if(anim>=8) {
                                lastTimes[i] = now;
                                twoDiamArray[i][iArray[i]].setStyle("-fx-background-color: #fff");
                                twoDiamArray[i][iArray[i]+1].setStyle("-fx-background-color: #fff");
                                iArray[i]+=1;
                            }
                        }
                    } else {
                        lastTimes[i] = now;
                        twoDiamArray[i][iArray[i]].setStyle("-fx-background-color: #fff");
                        iArray[i] = 0;
                        if (workeds[i] == 1) {
                            workeds[i] = 0;
                        } else {
                            workeds[i] = -1;
                        }
                    }
                }
            }
        }
    }
    public void unite(int n, long anim, long now){
        if(anim<=2){
            int i;
            for(i = 0; i<n;i++){
                if(twoDiamArray[i][0]!=null)
                twoDiamArray[i][0].setStyle("-fx-background-color: #2850f0"); // начальные элементы строк подсвечиваются
            }
        }else{
            if(anim<=4){
                if(minNumPart==-1) {
                    int k, j;
                    for (k = 0, j = 0; j < n; j++) {   // индекс строки с минимальным начальным элементом
                        if(twoDiamArray[j][0]!=null)
                        if (twoDiamArray[j][0].getNumber() < twoDiamArray[k][0].getNumber()) {
                            k = j;
                        }
                    }
                    minNumPart = k;
                }else{
                    int j;
                    for(j = 0; j<n; j++){
                        if(j!=minNumPart){
                            if(twoDiamArray[j][0]!=null)
                            twoDiamArray[j][0].setStyle("-fx-background-color: #FF0000");//все не минимальные элементы - красные
                        }
                    }
                    twoDiamArray[minNumPart][0].setStyle("-fx-background-color: #008000");//минимальный элемент - зелёный
                }
            }else{
                if(twoDiamArray[minNumPart][0].getLayoutX()>unitedElements*40){ // передвигаем минимальный элемент по x
                    twoDiamArray[minNumPart][0].setLayoutX(twoDiamArray[minNumPart][0].getLayoutX()-1);
                    for(int j = 1;j<n && twoDiamArray[minNumPart][j]!=null;j++) { // передвигаем все элементы в строке
                        if(twoDiamArray[minNumPart][j].getLayoutX()>(j-1)*40+(lengthArray*40)){
                            twoDiamArray[minNumPart][j].setLayoutX(twoDiamArray[minNumPart][j].getLayoutX()-1);
                        }
                    }
                }else{
                    if(n%2==1){
                        if(minNumPart<n/2){
                            if(twoDiamArray[minNumPart][0].getLayoutY()<(((int)(n/2))*100+50)) { // передвигаем по y минимальный элемент
                                twoDiamArray[minNumPart][0].setLayoutY(twoDiamArray[minNumPart][0].getLayoutY()+1);
                            }else{
                                ElementArrayList.getInstance().add(twoDiamArray[minNumPart][0]); // перенос элемента
                                unitedElements++;
                                int j;
                                for (j = 1; j < n; j++){
                                    twoDiamArray[minNumPart][j - 1] = twoDiamArray[minNumPart][j]; // Сдвиг сливаемой строки
                                }
                                twoDiamArray[minNumPart][n - 1] = new Element(ElementArrayList.getMaxValue() + 1); // Запись ограничителя
                                minNumPart=-1;
                                lastTime3=now;
                            }
                        }
                        if(minNumPart==n/2){
                            ElementArrayList.getInstance().add(twoDiamArray[minNumPart][0]); // перенос элемента
                            unitedElements++;
                            int j;
                            for (j = 1; j < n; j++){
                                twoDiamArray[minNumPart][j - 1] = twoDiamArray[minNumPart][j]; // Сдвиг сливаемой строки
                            }
                            twoDiamArray[minNumPart][n - 1] = new Element(ElementArrayList.getMaxValue() + 1); // Запись ограничителя
                            minNumPart=-1;
                            lastTime3=now;
                        }
                        if(minNumPart>n/2){
                            if(twoDiamArray[minNumPart][0].getLayoutY()>((int)(n/2))*100+50) { // передвигаем по y минимальный элемент
                                twoDiamArray[minNumPart][0].setLayoutY(twoDiamArray[minNumPart][0].getLayoutY()-1);
                            }else{
                                ElementArrayList.getInstance().add(twoDiamArray[minNumPart][0]); // перенос элемента
                                unitedElements++;
                                int j;
                                for (j = 1; j < n; j++) {
                                    twoDiamArray[minNumPart][j - 1] = twoDiamArray[minNumPart][j]; // Сдвиг сливаемой строки
                                }
                                twoDiamArray[minNumPart][n - 1] = new Element(ElementArrayList.getMaxValue() + 1); // Запись ограничителя
                                minNumPart=-1;
                                lastTime3=now;
                            }
                        }
                    }else{
                        if(minNumPart<n/2){
                            if(twoDiamArray[minNumPart][0].getLayoutY()<((n/2))*100) { // передвигаем по y минимальный элемент
                                twoDiamArray[minNumPart][0].setLayoutY(twoDiamArray[minNumPart][0].getLayoutY()+1);
                            }else{
                                ElementArrayList.getInstance().add(twoDiamArray[minNumPart][0]); // перенос элемента
                                unitedElements++;
                                int j;
                                for (j = 1; j < n; j++) {
                                    twoDiamArray[minNumPart][j - 1] = twoDiamArray[minNumPart][j]; // Сдвиг сливаемой строки
                                }
                                twoDiamArray[minNumPart][n - 1] = new Element(ElementArrayList.getMaxValue() + 1); // Запись ограничителя
                                minNumPart=-1;
                                lastTime3=now;
                            }
                        }
                        if(minNumPart>=n/2){
                            if(twoDiamArray[minNumPart][0].getLayoutY()>((n/2))*100) { // передвигаем по y минимальный элемент
                                twoDiamArray[minNumPart][0].setLayoutY(twoDiamArray[minNumPart][0].getLayoutY()-1);
                            }else{
                                ElementArrayList.getInstance().add(twoDiamArray[minNumPart][0]); // перенос элемента
                                unitedElements++;
                                int j;
                                for (j = 1; j < n; j++) {
                                    twoDiamArray[minNumPart][j - 1] = twoDiamArray[minNumPart][j]; // Сдвиг сливаемой строки
                                }
                                twoDiamArray[minNumPart][n - 1] = new Element(ElementArrayList.getMaxValue() + 1); // Запись ограничителя
                                minNumPart=-1;
                                lastTime3=now;
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean isRunning() {
        return running;
    }
}
