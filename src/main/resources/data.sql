-- Insert Categories
INSERT INTO categories (name, color_code, icon_code, is_featured) VALUES
('Birthdays', 'bg-teal-100', 'FaGift', true),
('Anniversaries', 'bg-rose-100', 'FaHeart', true),
('Parties', 'bg-purple-100', 'FaGlassCheers', true),
('Weddings', 'bg-orange-100', 'FaRing', false),
('Meetups', 'bg-blue-100', 'FaHandshake', false),
('Travel', 'bg-yellow-100', 'FaPlane', false),
('Gaming', 'bg-indigo-100', 'FaGamepad', false);

-- Insert Events
-- Sarah's Birthday (2 days from now)
INSERT INTO events (title, description, event_date, user_id, category_id)
VALUES ('Sarah''s Birthday', 'Turning 25 on Saturday', NOW() + INTERVAL '2 days', 1, 1);

-- Some event today for Calendar count
INSERT INTO events (title, description, event_date, user_id, category_id)
VALUES ('Team Meeting', 'Sync up', NOW(), 1, 5);

-- Insert Shopping Stats
INSERT INTO shopping_stats (label, amount, period, user_id) VALUES
('Others', 40.0, 'Current', 1),
('Flipkart', 65.0, 'Current', 1),
('Amazon', 90.0, 'Current', 1),
('Myntra', 55.0, 'Current', 1);