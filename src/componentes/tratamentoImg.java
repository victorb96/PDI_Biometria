package componentes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class tratamentoImg {
    
    private BufferedImage newImage = null;
    private String location = null;
    
    public tratamentoImg(){}
    
    public void convert(String location){
        try{
            BufferedImage newImg = ImageIO.read(new File(location));
            this.newImage = newImg;
            this.location = location;
            this.grayConvert(this.newImage);
        }catch(IOException e1){
            System.out.println("E1: "+e1);
        }
    }
    
    private void grayConvert(BufferedImage img){
        try{
            for(int j = 0; j < img.getHeight(); j++){
                for(int i = 0; i < img.getWidth(); i++){
                    
                    int pixel = img.getRGB(i, j);//obtem o valor do pixel
                    int alfa = (pixel>>24)&0xff;//obtem o valor alfa e aloca em uma posição fixa de memoria
                    int r = (pixel>>16)&0xff;//obtem o valor vermelho e aloca em uma posição fixa de memoria
                    int g = (pixel>>8)&0xff;//obtem o valor verde e aloca em uma posição fixa de memoria
                    int b = pixel&0xff;//obtem o valor azul e aloca em uma posição fixa de memoria
                    
                    //media dos valores RGB
                    int mediaRGB = (r+g+b)/3;
                    
                    pixel = (alfa<<24) | (mediaRGB<<16) | (mediaRGB<<8) | mediaRGB;
                    
                    img.setRGB(i, j, pixel);
                }
            }
            System.out.println("Imagem cinza");
            ImageIO.write(img, "JPG", new File(this.location.replaceAll(".jpg", "Gray.jpg")));
            this.limiarizacao(img);
        }catch(IOException e){
            System.out.println("Error: " +e);
        }
    }
    
    private void limiarizacao(BufferedImage img){
        try{
            int b = Color.BLACK.getRGB();
            int w = Color.WHITE.getRGB();
        
            for (int y = 0; y < img.getHeight(); y++){   
                for (int x = 0; x < img.getWidth(); x++){   
                    Color pixel = new Color(img.getRGB(x, y));   
                    img.setRGB(x, y, pixel.getRed() < 160 ? b : w);   
                }
            }
            System.out.println("Imagem preto e branco");
            ImageIO.write(img, "JPG", new File(this.location.replaceAll(".jpg", "BlackAndWhite.jpg")));
            this.esqueletizar(img, b, w);
        }catch(IOException e){
            System.out.println("DEU RUIM");
        }
    }
    
    private void esqueletizar(BufferedImage img, int pb, int pw){
        
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] colorsPixels = this.getColorPixels(img);
        
        System.out.println(x);
        System.out.println(y);
        try{
            for(int j = 0; j < y; j++){
                for(int i = 0; i < x; i++){
                    
                    //pega o pixel central 
                    int pixelCentral = colorsPixels[i][j];
                    int pixelDireito = 0;
                    int pixelDiInD = 0;
                    int pixelBaixo = 0;
                    int pixelDiInE = 0;
                    int pixelEsquerdo = 0;
                    int pixelDiSuE = 0;
                    int pixelCima = 0;
                    int pixelDiSuD = 0;
                    /*as condicionais verificam se existem os pixels vizinhos e  
                    *se existirem sao atribuidos as suas respectivas variaveis
                    */
                    //pixel a direita do central
                    if(i < x-1)pixelDireito = colorsPixels[i+1][j];
                    //pixel a diagonal inferior a direita do central
                    if(i < x-1 && j < y-1)pixelDiInD = colorsPixels[i+1][j+1];
                    //pixel abaixo do central
                    if(j < y-1 && i <= x)pixelBaixo = colorsPixels[i][j+1];
                    //pixel a diagonal inferior a esquerda do central
                    if(i != 0 && j < y-1)pixelDiInE = colorsPixels[i-1][j+1];
                    //pixel a esquerda do central
                    if(i != 0)pixelEsquerdo = colorsPixels[i-1][j];
                    //pixel a diagonal superior a esquerda do central
                    if(i != 0 && j != 0)pixelDiSuE = colorsPixels[i-1][j-1];
                    //pixel acima do central
                    if(j != 0 && i <= x)pixelCima = colorsPixels[i][j-1];
                    //pixel a diagonal superior a direita do central
                    if(i < x-1 && j != 0)pixelDiSuD = colorsPixels[i+1][j-1];
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private int[][] getColorPixels(BufferedImage img){
        
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] matrizCores = new int[x][y]; 
        
        for(int j = 0; j < y; j++){
            for(int i = 0; i < x; i++){
                matrizCores[i][j] = img.getRGB(i, j);
            }
        }
        
        return matrizCores;
    }
}