Model Design
- Entire model was changed from the previous assignment. The cells now hold strings, which are then converted into Sexpressions to be evaluated. Visitor classes are used to implement different functions that could be used on the cells.
- Instead of holding the evaluated expression, the cells hold the original expression, and are only evaluated through the model. This makes it so that the cells do not need to have access to other cells, rather it is the model spreadsheet that does all the calculations.

View Design
- The view takes in a model. The only part that interacts with the model is the view calling on the model to get a 2D array representation of its data to produce. From there, this data is passed into the two panels used to display the spreadsheet.
- SpreadsheetPanel is just the simple view of the spreadsheet, showing as far as the window size allows. It keeps track of how far it can see by tracking the size of the window, and only modifies cells that have data.
- SpreadhseetScrollPanel is in charge of scrolling, and calls specific functions created in SpreadsheetPanel to change fields that affect which cells are shown and which cells are displayed from the 2D arraylist from the model.
