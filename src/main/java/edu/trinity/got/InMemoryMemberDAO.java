package edu.trinity.got;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMemberDAO implements MemberDAO {
    private final Collection<Member> allMembers =
            MemberDB.getInstance().getAllMembers();

    @Override
    public Optional<Member> findById(Long id) {

        return allMembers.stream()
                .filter(member -> Objects.equals(member.id(), id))
                .findFirst();
    }

    @Override
    public Optional<Member> findByName(String name) {

        return allMembers.stream()
                .filter(member -> member.name().equals(name))
                .findFirst();
    }

    @Override
    public List<Member> findAllByHouse(House house) {

        return allMembers.stream()
                .filter(member -> member.house() == house)
                .toList();
    }

    @Override
    public Collection<Member> getAll() {

        return allMembers.stream()
                .toList();
    }

    /**
     * Find all members whose name starts with S and sort by id (natural sort)
     */
    @Override
    public List<Member> startWithSandSortAlphabetically() {

        return allMembers.stream()
                .filter(member -> member.name().startsWith("S"))
                .sorted()
                .toList();
    }

    /**
     * Final all Lannisters and sort them by name
     */
    @Override
    public List<Member> lannisters_alphabeticallyByName() {

        return allMembers.stream()
                .filter(member -> member.house() == House.LANNISTER)
                .sorted(Comparator.comparing(Member::name))
                .toList();
    }

    /**
     * Find all members whose salary is less than the given value and sort by house
     */
    @Override
    public List<Member> salaryLessThanAndSortByHouse(double max) {

        return allMembers.stream()
                .filter(member -> member.salary() < max)
                .sorted(Comparator.comparing(Member::house))
                .toList();
    }

    /**
     * Sort members by House, then by name
     */
    @Override
    public List<Member> sortByHouseNameThenSortByNameDesc() {

        return allMembers.stream()
                .sorted(Comparator.comparing(Member::house)
                        .thenComparing(Member::name).reversed())
                .toList();
    }

    /**
     * Sort the members of a given House by birthdate
     */
    @Override
    public List<Member> houseByDob(House house) {

        return allMembers.stream()
                .filter(member -> member.house() == house)
                .sorted(Comparator.comparing(Member::dob))
                .toList();
    }

    /**
     * Find all Kings and sort by name in descending order
     */
    @Override
    public List<Member> kingsByNameDesc() {

        return allMembers.stream()
                .filter(member -> member.title() == Title.KING)
                .sorted(Comparator.comparing(Member::name).reversed())
                .toList();
    }

    /**
     * Find the average salary of all the members
     */
    @Override
    public double averageSalary() {

        return allMembers.stream()
                .mapToDouble(Member::salary)
                .average()
                .orElse(0.0);
    }

    /**
     * Get the names of a given house, sorted in natural order
     * (note sort by _names_, not members)
     */
    @Override
    public List<String> namesSorted(House house) {

        return allMembers.stream()
                .filter(member -> member.house() == house)
                .map(Member::name)
                .sorted()
                .toList();
    }

    /**
     * Are any of the salaries greater than 100K?
     */
    @Override
    public boolean salariesGreaterThan(double max) {

        return allMembers.stream()
                .anyMatch(member -> member.salary() > max);
    }

    /**
     * Are there any members of given house?
     */
    @Override
    public boolean anyMembers(House house) {

        return allMembers.stream()
                .anyMatch(member -> member.house() == house);
    }

    /**
     * How many members of a given house are there?
     */
    @Override
    public long howMany(House house) {

        return allMembers.stream()
                .filter(member -> member.house() == house)
                .count();
    }

    /**
     * Return the names of a given house as a comma-separated string
     */
    @Override
    public String houseMemberNames(House house) {

        return allMembers.stream()
                .filter(member -> member.house() == house)
                .map(Member::name)
                .collect(Collectors.joining(", "));
    }

    /**
     * Who has the highest salary?
     */
    @Override
    public Optional<Member> highestSalary() {

        return allMembers.stream()
                .max(Comparator.comparingDouble(Member::salary));
    }

    /**
     * Partition members into royalty and non-royalty
     * (note: royalty are KINGs and QUEENs only)
     */
    @Override
    public Map<Boolean, List<Member>> royaltyPartition() {

        return allMembers.stream()
                .collect(Collectors.partitioningBy(
                        member -> member.title() == Title.KING || member.title() == Title.QUEEN));
    }

    /**
     * Group members into Houses
     */
    @Override
    public Map<House, List<Member>> membersByHouse() {
        return allMembers.stream()
                .collect(Collectors.groupingBy(Member::house));
    }

    /**
     * How many members are in each house?
     * (group by house, downstream collector using counting
     */
    @Override
    public Map<House, Long> numberOfMembersByHouse() {
        return allMembers.stream()
                .collect(Collectors.groupingBy(Member::house, Collectors.counting()));
    }

    /**
     * Get the max, min, and ave salary for each house
     */
    @Override
    public Map<House, DoubleSummaryStatistics> houseStats() {
        return allMembers.stream()
                .collect(Collectors.groupingBy(Member::house, Collectors.summarizingDouble(Member::salary)));
    }

    /**
     * Get 5 KINGs with the highest salaries
     */
    @Override
    public List<Member> top5HighestSalariesKingsThenSortByDOB() {
        return allMembers.stream()
                .filter(member -> member.title() == Title.KING)
                .sorted(Comparator.comparing(Member::salary).reversed()
                        .thenComparing(Member::dob))
                .limit(5)
                .toList();
    }
}
