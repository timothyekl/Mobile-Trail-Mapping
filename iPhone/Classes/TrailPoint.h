#import <Foundation/Foundation.h>

/**
 * @param id The ID of this TrailPoint
 * @param p The location of this TrailPoint
 * @param category What category this TrailPoint belongs to
 * @param summary A brief summary about this trail point
 * @param title The title of this TrailPoint 
 * @param connections A list of TrailPoints that this one is connected to
 */
@interface TrailPoint : NSObject {
    
	NSString* name;
	NSString* description;
	double latitude;
	double longitude;
}

@property (nonatomic, retain) NSString* name;
@property (nonatomic, retain) NSString* description;
@property (nonatomic) double latitude;
@property (nonatomic) double longitude;

@end
