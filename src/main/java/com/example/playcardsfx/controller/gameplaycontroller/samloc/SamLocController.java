package com.example.playcardsfx.controller.gameplaycontroller.samloc;

import com.example.playcardsfx.utilities.MediaManager;
import com.example.playcardsfx.model.Card;
import com.example.playcardsfx.model.Deck;
import com.example.playcardsfx.model.Player;
import com.example.playcardsfx.utilities.SceneManager;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;



import java.net.URL;
import java.util.*;

public class SamLocController implements Initializable {


    @FXML
    private Label centerHand;

    @FXML
    private ImageView hand11, hand12, hand13, hand14, hand15, hand16, hand17, hand18, hand19, hand110,
            hand21, hand22, hand23, hand24, hand25, hand26, hand27, hand28, hand29, hand210;

    @FXML
    private Button resetButton;

    @FXML
    private ImageView homeButton;
    @FXML
    private AnchorPane parent;

    //Luu day bai muon danh
    private ArrayList<Card> handOfPlayer1;
    private ArrayList<Card> handOfPlayer2;

    //Hinh anh cac la bai
    private ArrayList<ImageView> handsOfPlayer1;
    private ArrayList<ImageView> handsOfPlayer2;

    //Lop nguoi choi
    private Player player1;
    private Player player2;

    //Bo dem
    private int k;
    private CardRepresentative check;

    //Lop dai dien cac la bai
    private CardHelper turn, turnTest;
    //Kieu bai cua mot tap hop bai hien tai
    private CardRepresentative currentHand;
    private int count;
    private ArrayList<Integer> idx1, idx2;
    private int m;
    private int length1, length2, number1, number2, type1, type2;
    private int c1, c2;
    private CardRepresentative checkHand, test;
    private HBox hBox;
    private Map<ImageView, Boolean> cardStates;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MediaManager.getInstance().playBackgroundMusic("/MusicSource/BackgroundMusic/puzzle-game-bright-casual-video-game-music-249202.mp3",0.1);
        centerHand.setStyle("-fx-background-color: #8B0000;");
        //Khoi tao
        // tro choi moi
        Deck deck2 = new Deck();
        deck2.shuffle();
        count = 0;
        c1 = 10;
        c2 = 10;
        hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        centerHand.setAlignment(Pos.CENTER);
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        currentHand = new CardRepresentative(0, 0, 0);
        //chia bài
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                player1.addCard(deck2.dealCard());
            } else {
                player2.addCard(deck2.dealCard());
            }
        }
        // Tao mang luu cac la bai duoc chon
        idx1 = new ArrayList<>();
        idx2 = new ArrayList<>();


        test = new CardRepresentative(0, 0, 0);
        // Tao mang cac imageView de them anh
        handsOfPlayer1 = new ArrayList<>();
        Collections.addAll(handsOfPlayer1, hand11, hand12, hand13, hand14, hand15, hand16, hand17, hand18, hand19, hand110);
        handsOfPlayer2 = new ArrayList<>();
        Collections.addAll(handsOfPlayer2, hand21, hand22, hand23, hand24, hand25, hand26, hand27, hand28, hand29, hand210);

        for (int i = 0; i < 10; i++) {
            handsOfPlayer1.get(i).setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + player1.getCardsOfPlayer().get(i).getRank() + player1.getCardsOfPlayer().get(i).getSuit() + ".png")));
            handsOfPlayer2.get(i).setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + player2.getCardsOfPlayer().get(i).getRank() + player2.getCardsOfPlayer().get(i).getSuit() + ".png")));
        }
        handOfPlayer1 = new ArrayList<>();
        handOfPlayer2 = new ArrayList<>();
        cardStates = new HashMap<>();
        turnTest = new CardHelper();
    }

    // Thêm thao tác nhô lên hạ xuống của bài

    //Event click chuot vao la bai
    public void onCardClicked(MouseEvent mouseEvent) {
        // neu getSource ra kieu du lieu ImageView
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView clicked = (ImageView) mouseEvent.getSource();
            k = handsOfPlayer1.indexOf(clicked);

            if (k != -1) {
                // Lấy trạng thái của ImageView (true = đang ở trên, false = đang ở dưới)
                boolean isMovedUp = cardStates.getOrDefault(clicked, false);
                //Tao hand bai hien tai
                // Tạo hiệu ứng di chuyển
                TranslateTransition transition = new TranslateTransition(Duration.millis(50), clicked);
                if (!isMovedUp) {
                    transition.setToY(-20); // Di chuyển len tren
                    handOfPlayer1.add(player1.getCardOfPlayer(k));
                    idx1.add(k);
                    cardStates.put(clicked, true); // Cập nhật trạng thái

                } else {
                    transition.setToY(0); // Di chuyển ve cho ban dau
                    //Bo la bai ra khoi hand
                    handOfPlayer1.remove(player1.getCardOfPlayer(k));
                    idx1.remove(Integer.valueOf(k));
                    cardStates.put(clicked, false); // Cập nhật trạng thái

                }
                test = turnTest.generateRepresentative(handOfPlayer1);
                transition.play(); // Chạy hiệu ứng
            } else {
                k = handsOfPlayer2.indexOf(clicked);
                // Lấy trạng thái của ImageView (true = đang ở trên, false = đang ở dưới)
                boolean isMovedUp = cardStates.getOrDefault(clicked, false);
                // Tạo hiệu ứng di chuyển
                TranslateTransition transition2 = new TranslateTransition(Duration.millis(50), clicked);
                if (!isMovedUp) {
                    transition2.setToY(-20); // Di chuyển len tren
                    handOfPlayer2.add(player2.getCardOfPlayer(k));
                    idx2.add(k);
                    test = turnTest.generateRepresentative(handOfPlayer2);
                    System.out.println(test.getLength() + " " + test.getNumber() + " " + test.getType());
                    cardStates.put(clicked, true); // Cập nhật trạng thái

                } else {
                    transition2.setToY(0); // Di chuyển ve cho ban dau
                    //Bo la bai ra khoi hand
                    handOfPlayer2.remove(player2.getCardOfPlayer(k));
                    idx2.remove(Integer.valueOf(k));
                    cardStates.put(clicked, false); // Cập nhật trạng thái

                }


                transition2.play(); // Chạy hiệu ứng
            }
        }
    }


    public void danhBai1(ActionEvent actionEvent){
        turn = new CardHelper();
        if (currentHand.getLength() == 0 && currentHand.getNumber() == 0 && currentHand.getType() == 0 && count == 0) {
            checkHand = turn.generateRepresentative(handOfPlayer1);
            if (checkHand != null){
                currentHand = checkHand;
                m = idx1.size();
                hBox.getChildren().clear();
                centerHand.setGraphic(null);
                for (int i = 0; i < m; i++) {
                    hBox.getChildren().add(handsOfPlayer1.get(idx1.get(0)));
                    parent.getChildren().remove(handsOfPlayer1.get(idx1.get(0)));
                    idx1.remove(0);
                }
                centerHand.setGraphic(hBox);
                m = handOfPlayer1.size();
                handOfPlayer1.clear();
                count = 1;
                c1 -= m;
            }
        }
        else if(currentHand.getLength() == 1 && currentHand.getNumber() == 15 && currentHand.getType() == 1){
            check = turnTest.generateRepresentative(handOfPlayer1);
            if(check.getType() ==4){
                currentHand = check;
                m = idx1.size();
                hBox.getChildren().clear();
                centerHand.setGraphic(null);
                for (int i = 0; i < m; i++) {
                    hBox.getChildren().add(handsOfPlayer1.get(idx1.get(0)));
                    parent.getChildren().remove(handsOfPlayer1.get(idx1.get(0)));
                    handOfPlayer1.remove(idx1.get(0));
                    idx1.remove(0);
                    count = 1;
                }
                centerHand.setGraphic(hBox);
                m = handOfPlayer1.size();
                handOfPlayer1.clear();
                c1 -= m;
            }
        }

        else if (count == 0) {
            length1 = turnTest.generateRepresentative(handOfPlayer1).getLength();
            number1 = turnTest.generateRepresentative(handOfPlayer1).getNumber();
            type1 = currentHand.getType();
            System.out.println(currentHand.getLength() + " " + currentHand.getNumber() + " " + currentHand.getType());
            System.out.println(length1 + " " + number1 + " " + type1);
            if (currentHand.getLength() == length1 && currentHand.getNumber() < number1 && currentHand.getType() == type1) {
                hBox.getChildren().clear();
                centerHand.setGraphic(null);
                for(int i = 0; i < m; i++) {
                    hBox.getChildren().add(handsOfPlayer1.get(idx1.get(i)));
                }
                centerHand.setGraphic(hBox);
                currentHand = turnTest.generateRepresentative(handOfPlayer1);
                removeHand(handsOfPlayer1, handOfPlayer1, idx1);
                m = handOfPlayer1.size();
                handOfPlayer1.clear();
                c1 -= length1;
                count = 1;
            }
        }
        if(c1 == 0){
            System.out.println("Player 1 win");
        }
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/gambling.mp3", 1);

    }

    public void danhBai2(ActionEvent actionEvent) {
        turn = new CardHelper();
        if (currentHand.getLength() == 0 && currentHand.getNumber() == 0 && currentHand.getType() == 0 &&  count == 1) {
            check = turnTest.generateRepresentative(handOfPlayer2);
            if (check != null) {
                currentHand = turn.generateRepresentative(handOfPlayer2);
                m = idx2.size();
                hBox.getChildren().clear();
                centerHand.setGraphic(null);
                for (int i = 0; i < m; i++) {
                    hBox.getChildren().add(handsOfPlayer2.get(idx2.get(0)));
                    parent.getChildren().remove(handsOfPlayer2.get(idx2.get(0)));
                    idx2.remove(0);
                    count = 0;
                }
                centerHand.setGraphic(hBox);
                m = handOfPlayer2.size();
                for(int i = 0; i < m; i++){
                    System.out.print(handOfPlayer2.get(i).getCardIndex() + " ");
                    System.out.println();
                }
                handOfPlayer2.clear();

                c2 -= m;
            }
        }
        else if (count == 1) {
            length2 = turnTest.generateRepresentative(handOfPlayer2).getLength();
            number2 = turnTest.generateRepresentative(handOfPlayer2).getNumber();
            type2 = turnTest.generateRepresentative(handOfPlayer2).getType();
            System.out.println(currentHand.getLength() + " "  + currentHand.getNumber() + " " + currentHand.getType());
            System.out.println(length2 + " " + number2 + " " + type2);
            if (currentHand.getLength() == length2 && currentHand.getNumber() < number2 && currentHand.getType() == type2) {
                hBox.getChildren().clear();
                centerHand.setGraphic(null);
                for (int i = 0; i < m; i++) {
                    hBox.getChildren().add(handsOfPlayer2.get(idx2.get(i)));
                }
                centerHand.setGraphic(hBox);
                currentHand = turnTest.generateRepresentative(handOfPlayer2);
                removeHand(handsOfPlayer2, handOfPlayer2, idx2);
                c2 -= length2;
                for(int i = 0; i <handOfPlayer2.size(); i++){
                    System.out.print(handOfPlayer2.get(i).getCardIndex() + " ");
                    System.out.println();
                }
                handOfPlayer2.clear();
                count = 0;
            }
        }
        else if(currentHand.getLength() == 1 && currentHand.getNumber() == 15 && currentHand.getType() == 1){
            check = turnTest.generateRepresentative(handOfPlayer2);
            if(check.getType() ==4){
                currentHand = check;
                m = idx2.size();
                hBox.getChildren().clear();
                centerHand.setGraphic(null);
                for (int i = 0; i < m; i++) {
                    hBox.getChildren().add(handsOfPlayer2.get(idx2.get(0)));
                    parent.getChildren().remove(handsOfPlayer2.get(idx2.get(0)));
                    handOfPlayer2.remove(idx2.get(0));
                    idx2.remove(0);
                    count = 1;
                }
                centerHand.setGraphic(hBox);
                m = handOfPlayer2.size();
                for(int i = 0; i < m; i++){
                    System.out.print(handOfPlayer2.get(0).getCardIndex() + " ");
                    System.out.println();
                    handOfPlayer2.remove(0);
                }
                c2 -= m;
            }
        }


        if(c2 == 0){
            System.out.println("Player 2 win");
        }
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/gambling.mp3", 1);
    }



    public void boBai1(ActionEvent event) {
        // Người chơi 1 bỏ lượt, chuyển sang người chơi 2// Chuyển lượt
        currentHand = new CardRepresentative(0, 0, 0); // Đặt lại kiểu bài
        hBox.getChildren().clear();
        centerHand.setGraphic(null);
        handOfPlayer1.clear();
        idx1.clear();
        // Đặt lại trạng thái bài đã chọn
        //resetSelectedCards(handsOfPlayer1, idx1, handOfPlayer1);
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/select-sound-121244.mp3", 0.7);
        count = 1;
    }

    public void boBai2(ActionEvent event) {
        // Người chơi 2 bỏ lượt, chuyển sang người chơi 1
        // Chuyển lượt
        currentHand = new CardRepresentative(0, 0, 0); // Đặt lại kiểu bài
        hBox.getChildren().clear();
        centerHand.setGraphic(null);
        handOfPlayer2.clear();
        idx2.clear();
        // Đặt lại trạng thái bài đã chọn
        //resetSelectedCards(handsOfPlayer2, idx2, handOfPlayer2);
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/select-sound-121244.mp3", 0.7);
        count = 0;
    }



    // Đặt lại trạng thái bài đã chọn
    /*private void resetSelectedCards(ArrayList<ImageView> handsOfPlayer, ArrayList<Integer> idx, ArrayList<Card> handOfPlayer) {
        // Đặt lại các quân bài đã được nhấc lên
        if (!idx.isEmpty()) {
            for (int i : idx) {
                ImageView card = handsOfPlayer.get(i);
                TranslateTransition transition = new TranslateTransition(Duration.millis(50), card);
                transition.setToY(0); // Đưa bài về vị trí ban đầu
                transition.play();
                cardStates.put(card, false); // Cập nhật trạng thái
            }
            handOfPlayer.clear();
            idx.clear();
        }


    }

     */

    public void removeHand(ArrayList<ImageView> handsOfPlayer, ArrayList<Card> handOfPlayer, ArrayList<Integer> idx){
        int m = idx.size();
        for (int i = 0; i < m; i++) {
                parent.getChildren().remove(handsOfPlayer.get(idx.get(0)));
                handsOfPlayer.remove(idx.get(i));
        }
        handOfPlayer.clear();
        idx.clear();

    }

     public void resetButtonClicked(ActionEvent event){
        SceneManager.getInstance().setPrimaryStage((Stage)resetButton.getScene().getWindow());
         MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mixkit-water-sci-fi-bleep-902.mp3", 0.7);
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/SamLocScene.fxml", "/com/example/playcardsfx/stylefile/SamLocGameStyle.css");
    }

    public void homeButtonClick(MouseEvent event){
        SceneManager.getInstance().setPrimaryStage((Stage)homeButton.getScene().getWindow());
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mixkit-water-sci-fi-bleep-902.mp3", 0.7);
        MediaManager.getInstance().playBackgroundMusic("/MusicSource/BackgroundMusic/retro-gaming-271301.mp3",0.5);
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/StartMenuScene.fxml", "/com/example/playcardsfx/stylefile/Style.css");
    }
}













