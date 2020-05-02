package conferenceroom;

import conferenceroom.beans.Hour;
import conferenceroom.beans.Input;
import conferenceroom.beans.Room;
import conferenceroom.util.ClosestNumbers;

import java.util.*;

/**
 * Programming Test Conference room scheduling.
 *
 * Find the nearest open conference room for a team in which a team can hold its meeting.
 *
 * Given n team members with the floor on which they work and the time they want to meet, and a
 * list of conference rooms identified by their floor and room number as a decimal number,
 * maximum number of people it fits and pairs of times they are open - find the best place for the
 * team to have their meeting.
 *
 * If there is more than one room available that fits the team at the chosen time
 * then the best place is on the floor the closest to where the team works.
 *
 * E.g. rooms.txt
 * 7.11,8,9:00,9:15,14:30,15:00 8.23,6,10:00,11:00,14:00,15:00 8.43,7,11:30,12:30,17:00,17:30 9.511,9,9:30,10:30,12:00,12:15,15:15,16:15 9.527,4,9:00,11:00,14:00,16:00 9.547,8,10:30,11:30,13:30,15:30,16:30,17:30
 *
 * Input: 5,8,10:30,11:30 # 5 team members, located on the 8th floor, meeting time 10:30 - 11:30
 * Output: 9.547
 */

public class ConferenceRoom {

    public static void main(String[] args) {

        String rooms = "7.11,8,9:00,9:15,14:30,15:00 8.23,6,10:00,11:00,14:00,15:00 8.43,7,11:30,12:30,17:00,17:30 9.511,9,9:30,10:30,12:00,12:15,15:15,16:15 9.527,4,9:00,11:00,14:00,16:00 9.547,8,10:30,11:30,13:30,15:30,16:30,17:30 3.547,8,10:30,11:30,13:30,15:30,16:30,17:30 32.547,8,10:30,11:30,13:30,15:30,16:30,17:30 33.547,8,10:30,11:30,13:30,15:30,16:30,17:30";
        String input = "5,8,10:30,11:30";

        Map<Integer, List<Room>> floors = parseRooms(rooms);
        Input requestedRoom = parseSearch(input);

        findRoom(floors, requestedRoom);
    }

    private static void findRoom(Map<Integer, List<Room>> floors, Input requestedRoom) {

        // First we try to get the rooms on the same floor...
        if(floors.containsKey(requestedRoom.getFloor())){
            checkForRoom(requestedRoom.getFloor(), floors.get(requestedRoom.getFloor()), requestedRoom);
        }

        // If is not on the same floor, let's find the closest floors to search...
        Integer[] floorNumbers = floors.keySet().toArray(new Integer[floors.size()]);

        int[] array = new int[floors.size()];
        int index = 0;
        for(Integer element : floorNumbers)
                array[index++] = element.intValue();

        Arrays.sort(array);
        List<Integer> closestFloors = ClosestNumbers.listClosestNumbers(array, requestedRoom.getFloor(), array.length, array.length);

        System.out.println(closestFloors);

        for (Integer closestFloor : closestFloors) {
            checkForRoom(closestFloor, floors.get(closestFloor), requestedRoom);
        }
    }

    private static void checkForRoom(Integer floorNumber, List<Room> rooms, Input requestedRoom) {
        for (Room room : rooms) {

            Boolean match = Boolean.FALSE;
            // Check if room capacity is lower than the requested capacity and discard the room if its true
            if(room.getCapacity() < requestedRoom.getCapacity())
                continue;

            // Check if the hour requested is available by the list of hours
            for (Hour availableHour : room.getAvailableHours()) {
                if(isHourInInterval(requestedRoom.getStartTime(), availableHour.getStartTime(), availableHour.getEndTime())
                        && isHourInInterval(requestedRoom.getEndTime(), availableHour.getStartTime(), availableHour.getEndTime())){
                    match = Boolean.TRUE;
                    break;
                }
            }

            if(match) {
                System.out.println(floorNumber + "." + room.getNumber());
                System.exit(0);
            }
        }
    }

    private static Map<Integer, List<Room>> parseRooms(String rooms) {

        Map<Integer, List<Room>> floors = new HashMap<>();

        String[] roomsArray = rooms.split(" ");

        for (int i = 0; i < roomsArray.length; i++) {

            // Split the string array into an array of rooms with its details
            String[] roomDetail = roomsArray[i].split(",");

            Integer floorNumber = 0;
            Room room = new Room();

            floorNumber = Integer.parseInt(roomDetail[0].split("\\.")[0]);
            room.setNumber(Integer.parseInt(roomDetail[0].split("\\.")[1]));
            room.setCapacity(Integer.parseInt(roomDetail[1]));
            room.setAvailableHours(parseHours(roomDetail));

            if(!floors.containsKey(floorNumber)){
                List<Room> roomList = new ArrayList<>();
                roomList.add(room);
                floors.put(floorNumber, roomList);
            } else{
                List<Room> roomList = floors.get(floorNumber);
                roomList.add(room);
                floors.put(floorNumber, roomList);
            }

        }
        return floors;
    }

    /**
     * Input: 5,8,10:30,11:30 # 5 team members, located on the 8th floor, meeting time 10:30 - 11:30
     * @param input
     * @return
     */
    private static Input parseSearch(String input) {
        Input room = new Input();
        String[] roomDetail = input.split(",");

        room.setCapacity(Integer.parseInt(roomDetail[0]));
        room.setFloor(Integer.parseInt(roomDetail[1]));
        room.setStartTime(roomDetail[2]);
        room.setEndTime(roomDetail[3]);

        return room;
    }

    private static List<Hour> parseHours(String[] roomDetail) {
        List<Hour> hoursList = new ArrayList<>();
        // Iterate through the rest of the array, to get it's hours
        for (int k = 2; k < roomDetail.length; k++) {

            if (k + 1 >= roomDetail.length) {
                break;
            }

            Hour hour = new Hour();
            hour.setStartTime(roomDetail[k]);
            hour.setEndTime(roomDetail[k + 1]);
            hoursList.add(hour);
            k++;
        }

        return hoursList;
    }

    /**
     * @param  target  hour to check
     * @param  start   interval start
     * @param  end     interval end
     * @return true    true if the given hour is between
     */
    private static boolean isHourInInterval(String target, String start, String end) {
        return ((target.compareTo(start) >= 0)
                && (target.compareTo(end) <= 0));
    }

}
