package io.rdfs.controller;

import io.rdfs.helper.ConnectionReadySubscriber;
import io.rdfs.helper.DataHelper;
import io.rdfs.helper.EtherHelper;
import io.rdfs.helper.FileHelper;
import io.rdfs.model.File;
import io.rdfs.model.Offer;
import io.rdfs.view.FileListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FilesController implements Initializable {

    @FXML
    private Button connectButton;
    private boolean connected;

    @FXML
    private CheckBox acceptFilesCheckBox;

    @FXML
    private ListView<File> filesList;

    private ObservableList observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListView();
    }

    private void setListView() {
        DataHelper dataHelper = new DataHelper();
        List<File> files = dataHelper.getAllFiles();
        observableList.clear();
        observableList.addAll(files);

        filesList.setItems(observableList);
        filesList.setCellFactory(param -> new FileListCell());
    }

    @FXML
    public void handleAddFile(ActionEvent actionEvent) {
        EtherHelper etherHelper = EtherHelper.getInstance();
        File file = new File();

        try {
            etherHelper.publishOffer(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleConnect(ActionEvent actionEvent) {
        EtherHelper etherHelper = EtherHelper.getInstance();

        if(connected){
            etherHelper.disconnect();
            connectButton.setText("Connect");
        }else{
            etherHelper.connect(() -> {

            });
            connectButton.setText("Disconnect");
        }
    }

    public void handleAcceptFiles(ActionEvent actionEvent) {
        EtherHelper etherHelper = EtherHelper.getInstance();
        if(!acceptFilesCheckBox.isSelected()){
            etherHelper.unsubscribeToOffers();
        } else {
            etherHelper.subscribeToOffers();
        }
    }
}
