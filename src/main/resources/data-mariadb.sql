INSERT INTO player_profile (age, player_email, player_accepts_newsletter, player_username,player_no_played_games)
VALUES (11, 'F', TRUE, 'daniel',2),
       (22, 'M', TRUE, 'ddddd',3),
       (33, 'M', FALSE, 'ccccc',34),
       (44, 'F', TRUE, 'aaaaa',21);
INSERT INTO game_session (difficulty_level, player_id)
VALUES (5, 1), (6, 1),
       (1, 2), (2, 2),
       (87, 3), (33, 3), (21, 3),
       (17, 4), (32, 4), (23, 4), (33, 4);
INSERT INTO space_invader (coordinate_x, coordinate_y, game_session_id)
VALUES (12, 22,1), (14, 11,1), (23, 5,2), (43, 7,2), (8, 9,3), (13, 11,3);


