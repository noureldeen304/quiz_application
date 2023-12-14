INSERT INTO category (id, category_name)
VALUES (nextval('category_sequence'), 'Geography');

INSERT INTO category (id, category_name)
VALUES (nextval('category_sequence'), 'History');

INSERT INTO category (id, category_name)
VALUES (nextval('category_sequence'), 'Sports');

INSERT INTO category (id, category_name)
VALUES (nextval('category_sequence'), 'Science');

INSERT INTO category (id, category_name)
VALUES (nextval('category_sequence'), 'Movies');

-- =========================================================================

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 1, 'EASY', 'Paris', 'London', 'Berlin', 'Rome', 'What is the capital of France?', 'Paris');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 1, 'EASY', 'Tokyo', 'Beijing', 'Moscow', 'New Delhi', 'What is the capital of Japan?', 'Tokyo');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 1, 'MEDIUM', 'Sydney', 'Toronto', 'Cairo', 'Nairobi', 'What is the capital of Egypt?', 'Cairo');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 4, 'MEDIUM', 'Mercury', 'Venus', 'Mars', 'Jupiter', 'Which planet is closest to the Sun?', 'Mercury');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 2, 'EASY', 'Ancient Greece', 'Ancient Rome', 'Ancient Egypt', 'Ancient China', 'Which civilization built the Great Pyramids?', 'Ancient Egypt');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 3, 'MEDIUM', 'Football', 'Basketball', 'Tennis', 'Golf', 'Which sport is played in the FIFA World Cup?', 'Football');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 4, 'HARD', 'Albert Einstein', 'Isaac Newton', 'Nikola Tesla', 'Marie Curie', 'Who developed the theory of relativity?', 'Albert Einstein');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 1, 'EASY', 'Madrid', 'Amsterdam', 'Brussels', 'Vienna', 'What is the capital of Spain?', 'Madrid');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 2, 'MEDIUM', 'World War I', 'World War II', 'Cold War', 'Vietnam War', 'Which war ended in 1945?', 'World War II');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 4, 'HARD', 'Mitochondria', 'Nucleus', 'Cell membrane', 'Endoplasmic reticulum', 'Which organelle is known as the "powerhouse of the cell"?', 'Mitochondria');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 1, 'EASY', 'New York City', 'Los Angeles', 'Chicago', 'Houston', 'Which city is the largest in the United States?', 'New York City');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 3, 'MEDIUM', 'Roger Federer', 'Rafael Nadal', 'Novak Djokovic', 'Serena Williams', 'Who has won the most Grand Slam titles in men''s tennis?' , 'Novak Djokovic');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 2, 'HARD', 'French Revolution', 'American Revolution', 'Russian Revolution', 'Industrial Revolution', 'Which revolution took place in 1789 in France?', 'French Revolution');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 4, 'MEDIUM', 'DNA', 'RNA', 'ATP', 'mRNA', 'What is the genetic material that carries information in most organisms?', 'DNA');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 1, 'MEDIUM', 'New York', 'Los Angeles', 'Chicago', 'Houston', 'Which city is known as the "Big Apple"?', 'New York');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 3, 'EASY', 'Basketball', 'Football', 'Soccer', 'Tennis', 'Which sport is played with a round ball?', 'Soccer');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 5, 'MEDIUM', 'Leonardo DiCaprio', 'Brad Pitt', 'Tom Hanks', 'Robert Downey Jr.', 'Who played the lead role in the movie "Titanic"?', 'Leonardo DiCaprio');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 1, 'EASY', 'Sydney', 'Melbourne', 'Brisbane', 'Perth', 'What is the capital of Australia?', 'Canberra');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 3, 'EASY', 'Lionel Messi', 'Cristiano Ronaldo', 'Neymar', 'Kylian Mbappé', 'Who is considered one of the greatest football players of all time?', 'Lionel Messi');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 2, 'HARD', 'Napoleon Bonaparte', 'Alexander the Great', 'Genghis Khan', 'Julius Caesar', 'Who was the emperor of France in the early 19th century?', 'Napoleon Bonaparte');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 4, 'MEDIUM', 'Photosynthesis', 'Respiration', 'Fermentation', 'Transpiration', 'What is the process by which plants convert sunlight into energy?', 'Photosynthesis');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 3, 'HARD', 'Roger Federer', 'Rafael Nadal', 'Novak Djokovic', 'Andy Murray', 'Who holds the record for the most Grand Slam tennis singles titles?', 'Roger Federer');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 5, 'EASY', 'Tom Cruise', 'Dwayne Johnson', 'Will Smith', 'Brad Pitt', 'Who starred in the "Mission: Impossible" film series?', 'Tom Cruise');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 4, 'HARD', 'Albert Einstein', 'Isaac Newton', 'Stephen Hawking', 'Niels Bohr', 'Who formulated the theory of general relativity?', 'Albert Einstein');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 3, 'MEDIUM', 'New England Patriots', 'Pittsburgh Steelers', 'Green Bay Packers', 'Dallas Cowboys', 'Which team has won the most Super Bowl championships in NFL history?', 'New England Patriots');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 5, 'EASY', 'James Cameron', 'Christopher Nolan', 'Steven Spielberg', 'Quentin Tarantino', 'Who directed the movie "Avatar"?', 'James Cameron');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 1, 'MEDIUM', 'Amazon River', 'Nile River', 'Mississippi River', 'Yangtze River', 'Which is the longest river in the world?', 'Nile River');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 2, 'HARD', 'Martin Luther King Jr.', 'Nelson Mandela', 'Mahatma Gandhi', 'Winston Churchill', 'Who led the nonviolent resistance movement against racial segregation in the United States?', 'Martin Luther King Jr.');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'EASY', 'Lionel Messi', 'Cristiano Ronaldo', 'Neymar', 'Kylian Mbappé', 'Who is considered one of the greatest soccer players of all time?', 'Lionel Messi');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'HARD', 'The Godfather', 'Pulp Fiction', 'The Shawshank Redemption', 'Fight Club', 'Which movie is known for the line, "I''m gonna make him an offer he can''t refuse"?', 'The Godfather');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'HARD', 'Nelson Mandela', 'Mahatma Gandhi', 'Martin Luther King Jr.', 'Winston Churchill', 'Who said, "The only thing we have to fear is fear itself"?', 'Franklin D. Roosevelt');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'EASY', 'Mars', 'Venus', 'Jupiter', 'Saturn', 'Which planet is known as the "Red Planet"?', 'Mars');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 1, 'MEDIUM', 'Amazon River', 'Nile River', 'Yangtze River', 'Mississippi River', 'Which river is the longest in the world?', 'Nile River');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'MEDIUM', 'Daniel Craig', 'Pierce Brosnan', 'Roger Moore', 'Sean Connery', 'Which actor has portrayed James Bond in the most recent films?', 'Daniel Craig');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'MEDIUM', 'World War II', 'American Civil War', 'Vietnam War', 'Korean War', 'In which war did the United States drop atomic bombs on Hiroshima and Nagasaki?', 'World War II');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'MEDIUM', 'Leonardo DiCaprio', 'Tom Hanks', 'Brad Pitt', 'Johnny Depp', 'Which actor starred in "The Wolf of Wall Street"?', 'Leonardo DiCaprio');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'EASY', 'Basketball', 'Baseball', 'Football', 'Tennis', 'Which sport uses a round ball and a hoop?', 'Basketball');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'HARD', 'La La Land', 'Birdman', 'Moonlight', 'The Shape of Water', 'Which film won the Best Picture Oscar in 2017?', 'Moonlight');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'MEDIUM', 'Gravity', 'Electromagnetism', 'Strong nuclear force', 'Weak nuclear force', 'Which force is responsible for holding the planets in orbit around the sun?', 'Gravity');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'EASY', 'DNA', 'RNA', 'ATP', 'mRNA', 'What is the genetic material in cells?', 'DNA');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'EASY', 'Albert Einstein', 'Isaac Newton', 'Marie Curie', 'Charles Darwin', 'Who developed the theory of general relativity?', 'Albert Einstein');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'MEDIUM', 'Robert Downey Jr.', 'Chris Hemsworth', 'Mark Ruffalo', 'Chris Evans', 'Which actor plays the role of Iron Man in the Marvel Cinematic Universe?', 'Robert Downey Jr.');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'MEDIUM', 'Roger Federer', 'Rafael Nadal', 'Novak Djokovic', 'Andy Murray', 'Which tennis player has the most Grand Slam titles?', 'Roger Federer');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'HARD', '1945', '1918', '1941', '1954', 'In which year did World War II end?', '1945');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'HARD', 'Super Bowl', 'World Series', 'Stanley Cup', 'NBA Finals', 'Which championship is awarded to the winner of the annual football game in the United States?', 'Super Bowl');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 2, 'HARD', 'Julius Caesar', 'Augustus', 'Tiberius', 'Caligula', 'Which Roman emperor was known for building a vast empire?', 'Augustus');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 2, 'MEDIUM', 'American Revolution', 'French Revolution', 'Russian Revolution', 'Mexican Revolution', 'Which revolution led to the formation of the United States?', 'American Revolution');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence'), 2, 'EASY', 'Ancient Egypt', 'Ancient Rome', 'Ancient Greece', 'Ancient China', 'Which civilization built the Great Pyramids of Giza?', 'Ancient Egypt');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'EASY', 'Steven Spielberg', 'Martin Scorsese', 'Quentin Tarantino', 'Christopher Nolan', 'Who directed the film "Jurassic Park"?', 'Steven Spielberg');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'EASY', 'Basketball', 'Baseball', 'Football', 'Soccer', 'Which sport is played with a round ball and two teams of five players each?', 'Basketball');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'MEDIUM', 'FIFA World Cup', 'Super Bowl', 'Olympic Games', 'UEFA Champions League', 'Which event is considered the most prestigious in international soccer?', 'FIFA World Cup');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'HARD', 'Serena Williams', 'Maria Sharapova', 'Steffi Graf', 'Martina Navratilova', 'Which female tennis player has the most Grand Slam singles titles?', 'Margaret Court');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'MEDIUM', 'World War I', 'World War II', 'American Civil War', 'Vietnam War', 'Which war was fought between the Axis Powers and the Allied Powers from 1939 to 1945?', 'World War II');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'HARD', 'The Renaissance', 'The Industrial Revolution', 'The Enlightenment', 'The French Revolution', 'Which period in European history is known for its cultural and intellectual advancements?', 'The Renaissance');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'EASY', 'George Washington', 'Thomas Jefferson', 'Abraham Lincoln', 'John F. Kennedy', 'Who was the first President of the United States?', 'George Washington');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'MEDIUM', 'Photosynthesis', 'Respiration', 'Fermentation', 'Digestion', 'Which process converts sunlight into chemical energy in plants?', 'Photosynthesis');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'EASY', 'Gravity', 'Electromagnetism', 'Strong nuclear force', 'Weak nuclear force', 'Which force causes objects to fall towards the Earth?', 'Gravity');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'HARD', 'Niels Bohr', 'Max Planck', 'Erwin Schrödinger', 'Albert Einstein', 'Who developed the quantum theory of the atom?', 'Niels Bohr');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'EASY', 'Steven Spielberg', 'Martin Scorsese', 'Quentin Tarantino', 'Christopher Nolan', 'Who directed the film /"Jurassic Park"?', 'Steven Spielberg');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'MEDIUM', 'The Lord of the Rings: The Return of the King', 'Titanic', 'Forrest Gump', 'Gladiator', 'Which film won the Best Picture Oscar in 2004?', 'The Lord of the Rings: The Return of the King');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'MEDIUM', 'Alfred Hitchcock', 'Martin Scorsese', 'Steven Spielberg', 'Quentin Tarantino', 'Who directed the movie "Pulp Fiction"?', 'Quentin Tarantino');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'MEDIUM', 'Muhammad Ali', 'Mike Tyson', 'Floyd Mayweather Jr.', 'Manny Pacquiao', 'Who is considered "The Greatest" in boxing?', 'Muhammad Ali');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'EASY', 'Leonardo DiCaprio', 'Tom Hanks', 'Brad Pitt', 'Johnny Depp', 'Which actor starred in "Titanic"?', 'Leonardo DiCaprio');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'HARD', 'The Magna Carta', 'The Declaration of Independence', 'The Emancipation Proclamation', 'The Universal Declaration of Human Rights', 'Which document is considered a cornerstone of modern democracy?', 'The Magna Carta');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 1, 'HARD', 'Sahara Desert', 'Arabian Desert', 'Gobi Desert', 'Kalahari Desert', 'Which is the largest hot desert in the world?', 'Sahara Desert');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'MEDIUM', 'Gregor Mendel', 'Charles Darwin', 'Louis Pasteur', 'Marie Curie', 'Who is known as the "Father of Genetics"?', 'Gregor Mendel');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'HARD', 'The Big Bang Theory', 'The Theory of Evolution', 'The Theory of Relativity', 'The Theory of Plate Tectonics', 'Which theory explains the origin of the universe?', 'The Big Bang Theory');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 1, 'EASY', 'Australia', 'United States', 'China', 'India', 'Which country is the largest by land area?', 'Russia');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 1, 'MEDIUM', 'Mount Everest', 'K2', 'Kangchenjunga', 'Lhotse', 'Which is the highest mountain in the world?', 'Mount Everest');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'EASY', 'Roger Federer', 'Rafael Nadal', 'Novak Djokovic', 'Andy Murray', 'Which tennis player has won the most Wimbledon titles?', 'Roger Federer');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'EASY', 'Ancient Greece', 'Ancient Rome', 'Ancient Egypt', 'Ancient China', 'Which ancient civilization built the Great Pyramids?', 'Ancient Egypt');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 4, 'EASY', 'H2O', 'CO2', 'O2', 'NaCl', 'What is the chemical formula for water?', 'H2O');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 5, 'HARD', 'Katharine Hepburn', 'Meryl Streep', 'Ingrid Bergman', 'Audrey Hepburn', 'Which actress has won the most Academy Awards for Best Actress?', 'Katharine Hepburn');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 3, 'HARD', 'Brazil', 'Germany', 'Italy', 'Argentina', 'Which country has won the most FIFA World Cup titles?', 'Brazil');

INSERT INTO question (id, category_id, difficulty_level, option1, option2, option3, option4, question_title, answer)
VALUES (nextval('question_sequence') , 2, 'MEDIUM', 'The Battle of Waterloo', 'The Battle of Gettysburg', 'The Battle of Stalingrad', 'The Battle of Normandy', 'Which battle marked the final defeat of Napoleon Bonaparte?', 'The Battle of Waterloo');




