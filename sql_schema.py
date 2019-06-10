import sqlite3
import json
import sys

conn = sqlite3.connect('hotelList.db')
c = conn.cursor()

#Initializing table : hotels
c.execute('''
        DROP TABLE IF EXISTS `hotels`
        ''')
c.execute('''
        CREATE TABLE `hotels` (
            `hotelID`   INTEGER NOT NULL,
            `hotelStar` INTEGER NOT NULL,
            `locality` TEXT NOT NULL,
            `address` TEXT NOT NULL,
            PRIMARY KEY(`hotelID`)
        )''')

#Initializing table : rooms
c.execute('''
        DROP TABLE IF EXISTS `rooms`
        ''')
c.execute('''
        CREATE TABLE `rooms` (
                `hotelID` INTEGER NOT NULL,
                `roomType` INTEGER NOT NULL,
                `roomPrice` INTEGER NOT NULL,
                `number` INTEGER NOT NULL,
                PRIMARY KEY(`hotelID`, `roomType`)
        )''')

c.execute('''
        DROP TABLE IF EXISTS `requests`
        ''')
c.execute('''
        CREATE TABLE `requests` (
                `userID` INTEGER NOT NULL,
                `requestID` INTEGER NOT NULL,
                `hotelID` INTEGER NOT NULL,
                `startDate` TEXT NOT NULL,
                `endDate` TEXT NOT NULL,
                `single` INTEGER NOT NULL,
                `double` INTEGER NOT NULL,
                `quad` INTEGER NOT NULL,
                `price` INTEGER NOT NULL,
                PRIMARY KEY(`requestID`)
        )''')

#Reading hotel list file
room_dict = {"Single" : 1, "Double" : 2, "Quad" : 4}
with open(sys.argv[1], encoding="Big5") as hotel_file:
        data = json.load(hotel_file)
        for hotel in data:
                h_id = hotel["HotelID"]
                h_star = hotel["HotelStar"]
                h_locale = hotel["Locality"]
                h_address = hotel["Street-Address"]
                command = "INSERT INTO hotels VALUES ({}, {}, '{}', '{}')".format(h_id, h_star, h_locale, h_address)
                c.execute(command)
                for room in hotel["Rooms"]:
                        r_type = room_dict[room["RoomType"]]
                        r_price = room["RoomPrice"]
                        r_number = room["Number"]
                        command = "INSERT INTO rooms VALUES ({}, {}, {}, {})".format(h_id, r_type, r_price, r_number)
                        c.execute(command)
hotel_file.close()

conn.commit()
conn.close()