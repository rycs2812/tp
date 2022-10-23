package jarvis.ui;


import jarvis.model.Lesson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays the basic information of a {@code Lesson}.
 */
public class LessonCard extends UiPart<Region> {

    private static final String FXML = "LessonCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Lesson lesson;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private ImageView checkbox;
    @FXML
    private Label lessonType;
    @FXML
    private Label lessonDesc;
    @FXML
    private Label timePeriod;
    @FXML
    private Label lessonAttendance;

    Image tick = new Image(getClass().getResourceAsStream("/images/tick.png"));
    Image cross = new Image(getClass().getResourceAsStream("/images/cross.png"));

    /**
     * Creates a {@code LessonCard} with the given {@code Lesson} and index to display.
     */
    public LessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        id.setText(displayedIndex + ". ");
        if (lesson.isCompleted()) {
            checkbox.setImage(tick);
        } else {
            checkbox.setImage(cross);
        }
        lessonType.setText(lesson.getLessonType());
        lessonDesc.setText(lesson.getDesc().lessonDesc);
        timePeriod.setText(lesson.getTimePeriod().toString());
        lessonAttendance.setText(lesson.getStudentsName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonCard)) {
            return false;
        }

        // state check
        LessonCard card = (LessonCard) other;
        return id.getText().equals(card.id.getText())
                && lesson.equals(card.lesson);
    }

}
