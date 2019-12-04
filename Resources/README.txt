Provider View
- We were able to get most of the functionalities of the spreadsheet working
- Cells can be selected and edited
	- when a cell is selected, its contents (not evaluated, are displayed in the text box)
		- error in that sometimes you would need to click on a blank cell to "clear" the box before selecting another one with content
	- Clicking on confirm updates the cell content and displays it
	- functions ADD, PRODUCT, SUB, <, and APPEND
	- selected cell doesn't stay highlighted (JTable/view function we couldn't change), but stays "selected"
- cyclic references, (A1 holds B1, B1 holds A1)
- self references (A1 holds A1)
- Selecting cancel when editing a cell reverts the text box content back to its original content

How to use provider view:
- You need to first select a cell. It is highlighted black when you first select it, but once you select the text box to input, it removes the highlight. It is still selected however, and changes made with the confirm button will go to the last highlighted cell. 
- save and load weren't implemented since we had not done that in our own view, currently just two blank buttons that don't do anything if clicked on. 


Model Design Changes
- interface ISpreadSheet was added as a read-only interface for the model
- interface for spreadsheet added new function String getUnprocessedData(String c);
	- returns the unprocessed data of cell c
- In Worksheet line 46 - 87 UpdateCell made to only try to process the input cell to detect errors, so that users see error output instead of shutdown of app
- In Worksheet line 92 - 128 changes made for new cyclical checks. Only able to detect when cells directly reference each other
- In Worksheet line 358 - 372 changes made to getProcessedDataSheet. Was returning faulty data that wasn't caught in tests before hand
- In WorkSheet line 375 - 383 added new function to get unprocessed data from specified cells.

View Design Changes
- New interface to be implemented across all views added
	- textual and graphic views throw unsupported operation exception for unsupported functions
- In both text and graphic views, constructors no longer take in anything. Getting data to display is delegated to new function getDisplayData. No longer need model reference
	- rest of the two views were changed to match, too many small details
- For the graphical view, panels had to be fixed to work with corresponding model fixes 
	- was mostly small array indexing changes
- changes had to be added to the graphical view so that it could pass the appropriate data up for the editing view to send to the controller
	- function getCellName - returns name of cell clicked on (SpreadSheetPanel line 73)
	- function updateView - repaints and refreshes the view when new data is received (SpreadShee GraphicView 113)


Controller Design
- Controller had to do two main things, allow for selection of cells/displaying what it contains, and modifying the cell. 
- Controller was itself a mouse and an action listener. It set itself as the listener to the views, taking the signals which it then transferred to the model. The model returned information, which the controller then passed onto the view to be displayed. 


- To do so, I created a new view. The view was structured to create a graphical view, and then wrap additional components onto it, in this case, a textfield to show/alter cell content, and two buttons to confirm/reject changes. 

- Taking in the graphical view solved the problem of displaying the spreadsheet itself, but it couldn't show what it didn't know, like what the contents of the cells were before they were processed. To get that information, I made it so which cell is clicked on is passed from the controller down to the lowest panel, which returns the specific cell up to the graphical view, then to the controller, which then asks the model for the info. This info is then passed back into the view to be displayed in the text box. 

- Updating the cell was similar to checking what the cell contained before processing. This time, instead of only asking for something from the model, the controller updates the model and then gets a copy of the data to be displayed back, which it then sends to the view to update again. 