package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import be.Category;
import be.Movie;
import dal.DalException;
import gui.model.CatMovieModel;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Jacob, Christian, René & Charlie
 */
public class MainController implements Initializable
{

    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private CatMovieModel catMovieModel;
    private final ObservableList<Movie> searchedMovies;
    private boolean sortingByName;
    private boolean sortingByRating;
    private ObservableList<Movie> unwMovieList;
    private Category chosenCat;

    @FXML
    private ListView<Category> categoryView;

    @FXML
    private TextField searchField;
    @FXML
    private Button exitApp;
    @FXML
    private Button uwMovies;
    @FXML
    private ListView<Movie> uwMovieList;
    @FXML
    private TableView<Movie> movieView;
    @FXML
    private TableColumn<Movie, Double> imdbView;
    @FXML
    private TableColumn<Movie, String> nameView;
    @FXML
    private TableColumn<Movie, Double> ratingView;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            movieModel = MovieModel.getInstance();
            categoryModel = CategoryModel.getInstance();
            catMovieModel = CatMovieModel.getInstance();
            categoryView.setItems(categoryModel.getAllCategories());
        } catch (Exception ex)
        {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        movieView.setItems(movieModel.getAllMovies());
        // Sets all cells to their values for song table
        imdbView.setCellValueFactory(new PropertyValueFactory("imdbRating"));
        nameView.setCellValueFactory(new PropertyValueFactory("name"));
        ratingView.setCellValueFactory(new PropertyValueFactory("rating"));

    }

    /**
     * Constructor for the MainController.
     *
     * @throws Exception
     */
    public MainController() throws Exception
    {
        this.searchedMovies = FXCollections.observableArrayList();
    }

    /**
     * Opens the windows to create a new movie.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void movieNewButton(ActionEvent event) throws IOException
    {
        this.movieModel = movieModel;
        movieModel.setNewOrEdit("New Movie");
        Parent loader = FXMLLoader.load(getClass().getResource("MovieView.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the windows to edit a movie.
     *
     * @param event
     * @throws IOException
     * @throws DalException
     */
    @FXML
    private void movieEditButton(ActionEvent event) throws IOException, DalException
    {
        this.movieModel = movieModel;
        movieModel.setNewOrEdit("Edit Movie");
        Movie movie = movieView.getSelectionModel().getSelectedItem();
        movieModel.addSelectedMovie(movie);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MovieView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        MovieViewController controller = fxmlLoader.getController();
        controller.setModel(movieModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * Closes the application - with warning.... BUT WHY ?!
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void exitApp(ActionEvent event) throws IOException
    {
        // Popup stage with confirmation on exit of app.
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Exit", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Are you sure? ");
        // Close App.
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES)
        {
            // Get a handle to the stage
            Stage stage = (Stage) exitApp.getScene().getWindow();
            // Do what you have to do - Outrageous!
            stage.close();

        } else
        {
            // Close the popup stage.
            deleteAlert.close();
        }
    }

    /**
     * Search the movies, and sets the result as the view.
     *
     * @param event
     */
    @FXML
    private void searchMovie(KeyEvent event)
    {
        searchField.textProperty().addListener((observable, oldValue, newValue)
                ->
        {
            searchedMovies.setAll(movieModel.search(movieModel.getAllMovies(), newValue));
            movieView.setItems(searchedMovies);
        });
    }

    /**
     * Deletes the selected movie, and alerts the user.
     *
     * @param event
     * @throws DalException
     */
    @FXML
    private void movieDeleteButton(ActionEvent event) throws DalException
    {
        Movie selectedMovie = movieView.getSelectionModel().getSelectedItem();
        // Popup stage to confirm delete song.
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Are you sure you want to delete: " + selectedMovie.getName() + "?");
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES)
        {
            movieModel.deleteMovie(selectedMovie);
        } else
        {
            deleteAlert.close();
        }
    }

    /**
     * Opens the view to make a new category.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void categoryNewButton(ActionEvent event) throws IOException
    {
        this.categoryModel = categoryModel;
        categoryModel.setNewOrEdit("New Category");

        Parent loader = FXMLLoader.load(getClass().getResource("CategoryView.fxml"));

        Scene scene = new Scene(loader);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the view to edit the selected category.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void categoryEditButton(ActionEvent event) throws IOException
    {
        this.categoryModel = categoryModel;
        categoryModel.setNewOrEdit("Edit Category");
        Category category = categoryView.getSelectionModel().getSelectedItem();
        categoryModel.addSelectedCategory(category);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        CategoryViewController controller = fxmlLoader.getController();
        controller.setModel(categoryModel);
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * Deletes the selected category, and alerts the user.
     *
     * @param event
     * @throws DalException
     */
    @FXML
    private void categoryDeleteButton(ActionEvent event) throws DalException
    {
        Category selectedCategory = categoryView.getSelectionModel().getSelectedItem();
        // Popup stage to confirm delete song.
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Are you sure you want to delete: " + selectedCategory.getName() + "?");
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES)
        {
            categoryModel.deleteCategory(selectedCategory);
        } else
        {
            deleteAlert.close();
        }
    }

    /**
     * Plays the movie.
     *
     * @param event
     * @throws IOException
     * @throws DalException
     */
    @FXML
    private void playMovieButton(ActionEvent event) throws IOException, DalException
    {
        Movie watchMovie = movieView.getSelectionModel().getSelectedItem();
        movieModel.playMovie(watchMovie);
        movieModel.playedMovie(watchMovie);
    }

    /**
     * Shows the list of bad an unwatched movies in 2 years
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void uwMovies(ActionEvent event) throws IOException
    {
        uwMovieList.setItems(movieModel.unwMovieList());
    }

    /**
     * Sets the current selected category, so the movie view can be updated.
     *
     * @param event
     * @throws DalException
     */
    @FXML
    private void onCategoryViewClicked(MouseEvent event) throws DalException
    {
        try
        {
            chosenCat = categoryView.getSelectionModel().getSelectedItem();
            int checkCatId;
            checkCatId = chosenCat.getId();
            if (checkCatId == 1)
            {
                movieView.setItems(movieModel.getAllMovies());
            } else if (checkCatId == 2)
            {
                movieView.setItems(movieModel.getAllUnwatchedMovies());
            } else
            {
                movieView.setItems(catMovieModel.getMoviesFromCats(chosenCat));
            }
        } catch (DalException ex)
        {
            AlertWindow al = new AlertWindow();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
        }
    }

}
