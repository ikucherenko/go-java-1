package ua.com.goit.gojava1.lslayer.hackit2;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractCharaterTest {
    @Test
    public void testSmoke() {
        assertTrue(true);
    }
    @Test
    public void testSkillCreation() {
        Skill s = new Skill("SkillName");
        assertNotNull(s);
    }
    @Test
    public void testSkillName() {
        Skill s = new Skill("SkillName");
        assertEquals(s.getName(), "SkillName");
    }
    @Test 
    public void testSkillEquals() {
        Skill s1 = new Skill("TestSkillName");
        Skill s2 = new Skill("TestSkillName");
        assertTrue(s1.equals(s2));
        assertNotEquals(s1, null);
    }
    @Test
    public void testSkillValueFromActor() {
        Actor actor = new HumanControlledCharacter("Test name");
        actor.addSkill("test");
        assertEquals(actor.skillValue("test"), 1);
        assertNotEquals(actor.skillValue("test_skill"), 1);
    }
    @Test
    public void testSkillEvolve() {
        Actor actor = new HumanControlledCharacter("Test name");
        actor.addSkill("test");
        actor.evolveSkill("test");
        assertEquals(actor.skillValue("test"), 2);
        actor.evolveSkill("test_skill");
        assertEquals(actor.skillValue("test_skill"), 0);
        
    }
}
