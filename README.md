# S.P.A.C.C.A. Card Game

Admin credentials (not editable):
- Username: admin
- Password: admin1234

## How to Play

1. Once the application starts, the home screen is displayed with four main buttons:
   - the **GIOCA** button lets you join a match by entering its code;
   - the **Login Admin** button opens the admin menu for managing matches and players, accessible with the admin credentials;
   - the **Regole** button shows the screen containing the game rules;
   - the **Classifica** button shows the overall ranking of saved players, ordered by the points each player has earned in matches.

2. If you do not have a code to join an already created match, go to the admin login screen and enter **Gestione partite** to create a new competition.
   If there are no players, you can create them from the admin menu by entering the **Gestione giocatori** section.

3. When creating a match/tournament, you can select the competition settings, such as the participating players, BOT difficulty, and optionally the game mode.

4. Once a match/tournament has been created, a unique code will be returned. To play, log out of the admin account from the admin menu section and enter the generated code by clicking **GIOCA**.

5. After joining the match, the start screen will be displayed, showing the players taking part. Click anywhere on the screen to start the match.

6. At the end of the match, the ranking will be displayed with the points assigned to each player. By selecting **Esci**, you will be redirected to the home screen.

## Notes

- Player usernames uniquely identify each player. Users cannot have duplicate names, names starting with `BOT`, or the name `ADMIN`. The `ADMIN` player cannot be edited.
- BOTs are available in two modes, selectable when creating the competition: **EASY** and **HARD**. In **EASY** difficulty, the BOT randomly plays a card from its hand. In **HARD** difficulty, the BOT plays the first opportunity card it has in hand. If it has none, it plays the card with the highest damage or healing value depending on its health points. Like human players, BOTs are also forced to play unexpected event cards if they have any.
- Each competition is identified by a unique alphanumeric code, such as `A0000`. The initial letter is `S` for Single Matches, `T` for Simple Tournaments, and `L` for Last Man Standing Tournaments. The following four numeric digits are random and unique for each competition.
- Each card contains a section showing the effect associated with the card.

To read the complete game rules, click the **Regole** button on the home screen.

Have fun from the **Refactoring Knights**!

Baldari Cristian, Fuligni Francesco Maria, Zanolli Roberto.
